package com.sbmlmerge.core;

import com.sbmlmerge.gui.WizardFrame;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.StringEscapeUtils;
import org.sbml.jsbml.*;
import org.sbml.jsbml.xml.XMLNode;
import org.sbml.jsbml.xml.XMLTriple;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class MergeSBML {

    private SBLogger logger;
    private String[] speciesInfoKeys;
    private String[] reactionInfoKeys;
//    private Model model1, model2;
    private int[][] reactionMatchMatrix;
    private int threshold;
    private int similarityScoreThreshold;
    private double nameSimilarityThreshold;
    private double compartmentSimilarityThreshold;
    private double reactionSpeciesMatchThreshold;
    private Map<String, String> newSpeciesMap = null;
    private Map<String, String> newReactionMap = null;
    private Map<String, String> newFunctionMap = null;
    private Map<String, String> newCompartmentMap = null;
    private String hashStr;
    private int newSpeciesCounter;
    private int newReactionCounter;
    private int newFunctionCounter;
    private int newCompartmentCounter;
    private String newSpeciesIDPrefix;
    private String newReactionIDPrefix;
    private String newFunctionIDPrefix;
    private String newCompartmentIDPrefix;
    private String biomassId1;
    private String biomassId2;
    private int lastInternetFetchedIndex;
    private final WizardFrame parentFrame;

    public MergeSBML(WizardFrame parent, SBLogger log) {
        speciesInfoKeys = new String[]{"gene", "formula", "chebi", "kegg", "uniprot"};
        reactionInfoKeys = new String[]{"gene", "e.c.", "protein", "kegg", "uniprot"};
        threshold = 10;
        similarityScoreThreshold = 10;
        nameSimilarityThreshold = 0.97;
        compartmentSimilarityThreshold = 0.93;
        reactionSpeciesMatchThreshold = 1000;
        newCompartmentIDPrefix = "Comp_";
        logger = log;
        this.parentFrame = parent;
    }

    public boolean isEqualVersions(ArrayList<SBMLDocument> docs) {
        return (docs.get(0).getLevel() == docs.get(1).getLevel() &&
                docs.get(0).getVersion() == docs.get(1).getVersion());
    }

    public int standarding(ArrayList<SBMLDocument> sbmlDocList) {
        // for model1
        for (SBMLDocument doc : sbmlDocList) {
            for (Species species : doc.getModel().getListOfSpecies()) {
                standardizeNotes(species);
            }

            for (Reaction reaction : doc.getModel().getListOfReactions()) {
                standardizeNotes(reaction);
            }
        }
        return 0;
    }

    public static int usingInternetDB(Species sp) {
        Map<String, String> map;
        if (getValue(sp, "formula").isEmpty()) {
            map = getInfoFromInternet(sp.getName(), true);
        } else {
            map = getInfoFromInternet(sp.getName(), getValue(sp, "formula"), true);
        }

        if (map != null) {
            for (String key : map.keySet()) {
                if (map.get(key) != null) {
                    setValue(sp, key, map.get(key));
                }
            }
        }
        return 0;
    }

    public int usingInternetDB(Model model) throws XMLStreamException {
        int size = model.getListOfSpecies().size();
        for (int i = 0; i < model.getListOfSpecies().size(); i++) {
            Species sp = model.getSpecies(i);
            Map<String, String> map;
            if (getValue(sp, "formula").isEmpty()) {
                map = getInfoFromInternet(sp.getName(), true);
            } else {
                map = getInfoFromInternet(sp.getName(), getValue(sp, "formula"), true);
            }

            String keyValue = "";
            if (map != null) {
                for (String key : map.keySet()) {
                    if (map.get(key) != null) {
                        setValue(sp, key, map.get(key));
                        keyValue += key + ":" + map.get(key) + "\t";
                    }
                }
            }
            logger.info("[" + (i) + "/" + size + "]" + "[" + model.getName() + "]"
                    + "\tAdditional data for \"" + sp.getName() + "\" = " + keyValue);
        }
        return 0;
    }

    public int usingInternetDB(ArrayList<SBMLDocument> sbmlDocList,
            Thread runningThread, JTextArea txtArea, JProgressBar ProgressBar) {
        Model model1 = sbmlDocList.get(0).getModel();
        Model model2 = sbmlDocList.get(1).getModel();
        int size1 = model1.getListOfSpecies().size();
        int size2 = model2.getListOfSpecies().size();
        int total = size1 + size2;
        ProgressBar.setMinimum(0);
        ProgressBar.setMaximum(total);
        Document doc = txtArea.getDocument();
        int i, j;
        if (lastInternetFetchedIndex < size1) {
            if (lastInternetFetchedIndex != 0) {
                lastInternetFetchedIndex++;
            }
            i = lastInternetFetchedIndex;
            j = 0;
        } else {
            i = size1;
            j = lastInternetFetchedIndex - size1;
        }
        for (; i < size1; i++, lastInternetFetchedIndex++) {
            boolean isInterrupted = runningThread.isInterrupted();
            Species sp = model1.getSpecies(i);
            Map<String, String> map;
            if (getValue(sp, "formula").isEmpty()) {
                map = getInfoFromInternet(sp.getName(), true);
            } else {
                map = getInfoFromInternet(sp.getName(), getValue(sp, "formula"), true);
            }

            String keyValue = "";
            if (map != null) {
                for (String key : map.keySet()) {
                    if (map.get(key) != null) {
                        setValue(sp, key, map.get(key));
                        keyValue += key + ":" + map.get(key) + "\t";
                    }
                }
            }
            
            String log = logger.info("[" + (i+1) + "/" + total + "]" + "[" + model1.getName() + "]"
                    + "\tAdditional data for \"" + sp.getName() + "\" = " + keyValue);
            try {
                isInterrupted = runningThread.isInterrupted();
                doc.insertString(doc.getLength(), log + "\n", null);
            } catch (BadLocationException ex) {
                Logger.getLogger(MergeSBML.class.getName()).log(Level.SEVERE, null, ex);
                
            }
            ProgressBar.setValue(i+1);
            if (isInterrupted) {
                try {
                    doc.insertString(doc.getLength(), "Thread interrupted...\n", null);
                } catch (BadLocationException ex) {
                    Logger.getLogger(MergeSBML.class.getName()).log(Level.SEVERE, null, ex);
                }
                return 0;
            }
        }

        for (; j < size2; j++, lastInternetFetchedIndex++) {
            boolean isInterrupted = runningThread.isInterrupted();
            Species sp = model1.getSpecies(j);
            Map<String, String> map;
            if (getValue(sp, "formula").isEmpty()) {
                map = getInfoFromInternet(sp.getName(), true);
            } else {
                map = getInfoFromInternet(sp.getName(), getValue(sp, "formula"), true);
            }

            String keyValue = "";
            if (map != null) {
                for (String key : map.keySet()) {
                    if (map.get(key) != null) {
                        setValue(sp, key, map.get(key));
                        keyValue += key + ":" + map.get(key) + "\t";
                    }
                }
            }
            String log = logger.info("[" + (i + j + 1) + "/" + total + "]" + "[" + model2.getName() + "]"
                    + "\tAdditional data for \"" + sp.getName() + "\" = " + keyValue);
            try {
                isInterrupted = runningThread.isInterrupted();
                doc.insertString(doc.getLength(), log + "\n", null);
            } catch (BadLocationException ex) {
                Logger.getLogger(MergeSBML.class.getName()).log(Level.SEVERE, null, ex);
            }
            ProgressBar.setValue(i + j + 1);
            
            if (isInterrupted) {
                try {
                    doc.insertString(doc.getLength(), "Thread interrupted...\n", null);
                } catch (BadLocationException ex) {
                    Logger.getLogger(MergeSBML.class.getName()).log(Level.SEVERE, null, ex);
                }
                return 0;
            }
        }
        return 0;
    }

    public SBMLDocument Merge(ArrayList<SBMLDocument> sbmlDocList) throws XMLStreamException {
        newSpeciesMap = new HashMap<>();
        newReactionMap = new HashMap<>();
        newFunctionMap = new HashMap<>();
        newCompartmentMap = new HashMap<>();
        newSpeciesCounter = 1;
        newReactionCounter = 1;
        newFunctionCounter = 1;
        newCompartmentCounter = 1;
        hashStr = hash(sbmlDocList);
        newSpeciesIDPrefix = "SP" + "_" + hashStr + "_";
        newReactionIDPrefix = "Re" + "_" + hashStr + "_";
        newFunctionIDPrefix = "Fu" + "_" + hashStr + "_";
        biomassId1 = "";
        biomassId2 = "";
        String newId = "Merged_";
        for (SBMLDocument doc : sbmlDocList) {
            newId += doc.getModel().getId() + "_";
        }

        // create new model
        SBMLDocument newDoc = new SBMLDocument(sbmlDocList.get(0).getLevel(), sbmlDocList.get(0).getVersion());
        Model newModel = newDoc.createModel(newId);
        History hist = newModel.getHistory();
        Creator creator = new Creator("Given Name", "Family Name", "Organisation", "My@EMail.com");
        hist.addCreator(creator);

        for (SBMLDocument doc : sbmlDocList) {
            // add functions of two model to new model
            for (FunctionDefinition function : doc.getModel().getListOfFunctionDefinitions()) {
                addFunctionDefinition(newModel, function);
            }

            // add unit definition of two model to newModel
            for (UnitDefinition unitDefinition : doc.getModel().getListOfUnitDefinitions()) {
                addUnitDefinition(newModel, unitDefinition);
            }

            // add compartments of two model to newModel
            for (Compartment comp : doc.getModel().getListOfCompartments()) {
                addCompartment(newModel, comp);
            }

            // add parameters of two model to newModel
            for (Parameter para : doc.getModel().getListOfParameters()) {
                addParameter(newModel, para);
            }

            // add Rules of two model to newModel
            for (Rule rule : doc.getModel().getListOfRules()) {
                addRule(newModel, rule);
            }

            // add Events of two model to newModel
            for (Event event : doc.getModel().getListOfEvents()) {
                addEvent(newModel, event);
            }
        }

        for (Reaction reaction
                : sbmlDocList.get(0).getModel().getListOfReactions()) {
            addReaction(newModel, reaction);
        }

        for (Reaction reaction
                : sbmlDocList.get(1).getModel().getListOfReactions()) {
            // if this reaction has no similar reaction in model1
            // or it has , but similarity score is lower than 10
            if (getBestSimilarScore(reaction) <= threshold) {
                addReaction(newModel, reaction);
            }
        }
        return newDoc;
    }

// concat prefix and index with special format to create newId
    private String createNewID(String prefix, int index) {
        return prefix + String.format("%05d", index);
    }

    // add function definition to new model and return new Id
    private String addFunctionDefinition(Model newModel, FunctionDefinition function) {
        // if function already added to new model, do not add again, just return newID
        String newId;
        if ((newId = newFunctionMap.get(function.getId())) != null) {
            return newId;
        }

        newId = createNewID(newFunctionIDPrefix, newFunctionCounter++);
        newFunctionMap.put(function.getId(), newId);

        FunctionDefinition newFunction = function.clone();
        newFunction.setId(newId);
        try {
            newModel.addFunctionDefinition(newFunction);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
        return newId;
    }

    // add unit definition to new model and return new Id
    private String addUnitDefinition(Model newModel, UnitDefinition unitDefinition) {
        // if this unitDefinition already added to new model, do not add again, just return newID
        if (newModel.getUnitDefinition(unitDefinition.getId()) != null) {
            return unitDefinition.getId();
        }

        try {
            newModel.addUnitDefinition(unitDefinition.clone());
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
        return unitDefinition.getId();
    }

    // add parameters to new model and return new Id
    private String addParameter(Model newModel, Parameter parameter) {
        // if this unitDefinition already added to new model, do not add again, just return newID
        if (newModel.getParameter(parameter.getId()) != null) {
            return parameter.getId();
        }

        try {
            newModel.addParameter(parameter.clone());
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
        return parameter.getId();
    }

    // add rule to new model and return variable SId
    private String addRule(Model newModel, Rule rule) {
        try {
            newModel.addRule(rule.clone());
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
        if (rule instanceof AlgebraicRule) {
            return ""; // algebraicRule has no variable
        } else if (rule instanceof AssignmentRule) // if rule is instanceof AssignmentRule
        {
            return ((AssignmentRule) rule).getVariable();
        } else // if rule is instanceof RateRule
        {
            return ((RateRule) rule).getVariable();
        }
    }

    // add event to new model and return sid
    private String addEvent(Model newModel, Event event) {
        try {
            newModel.addEvent(event.clone());
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
        return event.getId();
    }

    // add compartment definition to new model and return new Id , if can not add Compartment retuen @null
    private String addCompartment(Model newModel, Compartment compartment) {
        String[] internalNames = {"internal", "internal member", "internal memberane", "internal space",
            "intra cellular", "intracellular", "intracellular space", "Internal_Species"};
        for (String name : internalNames) {
            if (isSimilar(compartment.toString().toLowerCase(), name) >= compartmentSimilarityThreshold) {
                compartment.setName("cytoplasm");
            }
        }

        String[] externalNames = {"external", "external membrane", "external member", "external space",
            "extra cellular", "extracellular space", "extracellular", "External_Species"};
        for (String name : externalNames) {
            if (isSimilar(compartment.toString().toLowerCase(), name) >= compartmentSimilarityThreshold) {
                compartment.setName("external");
            }
        }

        String newId = null;

        for (String key : newCompartmentMap.keySet()) // if Compartment already added to new model, do not add again, just return newID
        {
            if (isSimilar(key.toLowerCase(), compartment.toString().toLowerCase()) >= (compartmentSimilarityThreshold)) {
                newId = newCompartmentMap.get(key);
            }
        }

        if (newId != null) {
            newCompartmentMap.put(compartment.getName().toLowerCase(), newId);
        }
        if (newId == null) {
            //TODO if compartment.outside is setted, we should handle it here ...
            newId = createNewID(newCompartmentIDPrefix, newCompartmentCounter++);
            Compartment newCompartment = compartment.clone();

            if (!compartment.getOutside().isEmpty()) {
                String outsideId = addCompartment(newModel, compartment.getOutsideInstance());
                try {
                    newCompartment.setOutside(outsideId);
                } catch (Exception e) {
                }
            }
            // if this compartment's name is not set, use it's old id as name
            if (newCompartment.getName().isEmpty()) {
                newCompartment.setName(newCompartment.getId());
            }
            // set newId for this compartment
            newCompartment.setId(newId);

            try {
                newModel.addCompartment(newCompartment);
            } catch (Exception e) {
                e.printStackTrace(System.out);
                return null;
            }
            newCompartmentMap.put(compartment.toString().toLowerCase(), newId);
            newCompartmentMap.put(compartment.toString().toLowerCase() + " member", newId);
            newCompartmentMap.put(compartment.toString().toLowerCase() + " space", newId);
            newCompartmentMap.put(compartment.toString().toLowerCase() + " membrane", newId);
        }
        return newId;
    }

    // add species to newModel and return new SpeciesID, if can not add species, return @null
    private String addSpecies(Model newModel, Species species) throws XMLStreamException {
        // if species already added to new model, do not add again, just return newID
        String newId;
        if ((newId = newSpeciesMap.get(species.getId())) != null) {
            return newId;
        }

        newId = createNewID(newSpeciesIDPrefix, newSpeciesCounter++);
        newSpeciesMap.put(species.getId(), newId);

        Species newSpecies = species.clone();
        newSpecies.setId(newId);
        deleteValue(newSpecies, "similar to");
        if (getValue(newSpecies, "oldId").isEmpty()) {
            setValue(newSpecies, "oldId", species.getId());
        }
        String newCompartmentId = newCompartmentMap.get(species.getCompartmentInstance().getName().toLowerCase());
        newSpecies.setCompartment(newCompartmentId);

        try {
            newModel.addSpecies(newSpecies);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
        return newId;
    }

    // add reaction to newModel and return new ReactionID
    private String addReaction(Model newModel, Reaction reaction) throws XMLStreamException {
        // if Reaction already added to new model, do not add again, just return newID
        String newId;
        if ((newId = newReactionMap.get(reaction.getId())) != null) {
            return newId;
        }

        newId = createNewID(newReactionIDPrefix, newReactionCounter++);
        newReactionMap.put(reaction.getId(), newId);

        Reaction newReaction = reaction.clone();
        newReaction.setId(newId);
        deleteValue(newReaction, "similar to");
        if (getValue(reaction, "oldId").isEmpty()) {
            setValue(newReaction, "oldId", reaction.getId());
        }

        Model oldModel = reaction.getModel();

        for (SpeciesReference reactant : newReaction.getListOfReactants()) {
            reactant.setSpecies(addSpecies(newModel, oldModel.getSpecies(reactant.getSpecies())));
        }

        for (SpeciesReference product : newReaction.getListOfProducts()) {
            product.setSpecies(addSpecies(newModel, oldModel.getSpecies(product.getSpecies())));
        }

        for (ModifierSpeciesReference modifier : newReaction.getListOfModifiers()) {
            modifier.setSpecies(addSpecies(newModel, oldModel.getSpecies(modifier.getSpecies())));
        }

        if (newReaction.isSetCompartment()) {
            newReaction.setCompartment(newCompartmentMap.get(reaction.getCompartment().toLowerCase()));
        }

        try {
            newModel.addReaction(newReaction);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }

        return newId;
    }

    public void findSimilarSpecies(ArrayList<SBMLDocument> sbmlDocList) throws Exception {
//        speciesMatchMatrix = new int[model1.getSpeciesCount()][model2.getSpeciesCount()];
        int counter = 0;
        for (int i = 0; i < sbmlDocList.size(); i++) {
            for (int j = i + 1; j < sbmlDocList.size(); j++) {
                for (Species species1 : sbmlDocList.get(i).getModel().getListOfSpecies()) {
                    for (Species species2 : sbmlDocList.get(j).getModel().getListOfSpecies()) {
                        int score = 0;

//                      // if one species is external and another one is not, they are not similar at all...
//                      if (isExternalSpecies(species1) != isExternalSpecies(species2))
//                         continue;
                        // compare species Ids and MetaIds
                        if (isEqual(species1.getMetaId(), species2.getMetaId())
                                || isEqual(species1.getId(), species2.getId())
                                || isEqual(species1.getMetaId(), species2.getId())
                                || isEqual(species1.getId(), species2.getMetaId())) {
                            score += 10;
                        }

                        // compare Species Names
                        // isSimilar return a value between 0 and 1
                        double nameSimilarityScore = isSimilar(species1.getName(), species2.getName());
                        if (nameSimilarityScore >= nameSimilarityThreshold) {
                            score += 100;
                        }

                        // score 1000 is for species which assume similar , after find two similar reactions
                        // compare species Gene-id, Formula, Chebi-id, kegg-id, uniprot-id
                        for (String key : speciesInfoKeys) //TODO handle species which has keys with multiple values like "GENE ID : 01xXsd 23DCXed"
                        {
                            if (isEqual(getValue(species1, key).split(" ")[0], getValue(species2, key).split(" ")[0])) {
                                score += 10000;
                            }
                        }

                        if (score >= similarityScoreThreshold) {
                            setValue(species1, "similar to", species2.getId() + "," + score);
                            setValue(species2, "similar to", species1.getId() + "," + score);
//                          speciesMatchMatrix[i][j] = score;
                        }
                    }
                    ArrayList<SBMLDocument> otherDocs = new ArrayList<>(sbmlDocList);
                    otherDocs.remove(i);
                    printSimilarSpeciesOf(species1, otherDocs);
                    if (hasSimilarSpecies(species1)) // if species1 has similar species
                    {
                        counter++;
                    }
                }
            }
        }
        logger.info("\n\n"
                + "===================\n"
                + "number of similar species founded : " + counter + "\n"
                + "===================\n"
                + "\n");
    }

    public void findSimilarSpecies(
            ArrayList<SBMLDocument> sbmlDocList,
            JTextArea txtArea,
            JProgressBar ProgressBar) {

        ProgressBar.setMinimum(0);
        ProgressBar.setMaximum(sbmlDocList.get(0).getModel().getSpeciesCount());
        Document doc = txtArea.getDocument();
//        speciesMatchMatrix = new int[model1.getSpeciesCount()][model2.getSpeciesCount()];
        long counter = 0;
        for (int i = 0; i < sbmlDocList.size(); i++) {
            for (int j = i + 1; j < sbmlDocList.size(); j++) {
                int pbarIndex = 0;
                for (Species species1 : sbmlDocList.get(i).getModel().getListOfSpecies()) {
                    for (Species species2 : sbmlDocList.get(j).getModel().getListOfSpecies()) {
                        int score = 0;

//                      // if one species is external and another one is not, they are not similar at all...
//                      if (isExternalSpecies(species1) != isExternalSpecies(species2))
//                         continue;
                        // compare species Ids and MetaIds
                        if (isEqual(species1.getMetaId(), species2.getMetaId())
                                || isEqual(species1.getId(), species2.getId())
                                || isEqual(species1.getMetaId(), species2.getId())
                                || isEqual(species1.getId(), species2.getMetaId())) {
                            score += 10;
                        }

                        // compare Species Names
                        // isSimilar return a value between 0 and 1
                        double nameSimilarityScore = isSimilar(species1.getName(), species2.getName());
                        if (nameSimilarityScore >= nameSimilarityThreshold) {
                            score += 100;
                        }

                        // score 1000 is for species which assume similar , after find two similar reactions
                        // compare species Gene-id, Formula, Chebi-id, kegg-id, uniprot-id
                        for (String key : speciesInfoKeys) //TODO handle species which has keys with multiple values like "GENE ID : 01xXsd 23DCXed"
                        {
                            if (isEqual(getValue(species1, key).split(" ")[0], getValue(species2, key).split(" ")[0])) {
                                score += 10000;
                            }
                        }

                        if (score >= similarityScoreThreshold) {
                            setValue(species1, "similar to", species2.getId() + "," + score);
                            setValue(species2, "similar to", species1.getId() + "," + score);
//                      speciesMatchMatrix[i][j] = score;
                        }
                    }
                    ArrayList<SBMLDocument> otherDocs = new ArrayList<>(sbmlDocList);
                    otherDocs.remove(i);
                    String log = printSimilarSpeciesOf(species1, otherDocs);
                    try {
                        if (!log.trim().equals("")) {
                            doc.insertString(doc.getLength(), log, null);
                        }
                    } catch (BadLocationException ex) {
                        logger.info(ex.getMessage());
                    }
                    if (hasSimilarSpecies(species1)) // if species1 has similar species
                    {
                        counter++;
                    }
                    ProgressBar.setValue(++pbarIndex);
                }
            }
        }
        String log = logger.info("\n\n"
                + "===================\n"
                + "number of similar species founded : " + counter + "\n"
                + "===================\n"
                + "\n");
        parentFrame.setNumOfSimilarSpecies(counter);
        try {
            if (!log.equals("")) {
                doc.insertString(doc.getLength(), log, null);
            }
        } catch (BadLocationException ex) {
            logger.info(ex.getMessage());
        }
    }

    public static void setSimilarityManually(Species sp1, Species sp2, int score) {
//        deleteValue(sp1, "similar to");
        setValue(sp1, "similar to", sp2.getId() + "," + score);
//        deleteValue(sp2, "similar to");
        setValue(sp2, "similar to", sp1.getId() + "," + score);
    }

    public static void clearSimilarity(Species sp) {
        deleteValue(sp, "similar to");
    }

    public static void clearAllSimilarities(SBMLDocument doc) {
        for (Species sp : doc.getModel().getListOfSpecies()) {
            deleteValue(sp, "similar to");
        }
        for (Reaction re : doc.getModel().getListOfReactions()) {
            deleteValue(re, "similar to");
        }
    }

    public static void setSimilarity(Reaction re1, Reaction re2, int score) {
        deleteValue(re1, "similar to");
        setValue(re1, "similar to", re2.getId() + "," + score);
        deleteValue(re2, "similar to");
        setValue(re2, "similar to", re1.getId() + "," + score);
    }

    public static void clearSimilarity(Reaction re) {
        deleteValue(re, "similar to");
    }

    private String printSimilarSpeciesOf(Species sp, ArrayList<SBMLDocument> otherDocList) {
        String similarIds = getValue(sp, "similar to");
        String logString = "";
        if (!similarIds.isEmpty()) {
            String[] idScores = similarIds.split(" ");
            for (String idScore : idScores) {
                String name = "";
                for (SBMLDocument doc : otherDocList) {
                    if (doc.getModel().getSpecies(getId(idScore)) != null) {
                        name = doc.getModel().getSpecies(getId(idScore)).getName();
                        break;
                    }
                }
                logString += logger.info("Species \"" + sp.getName() + "\""
                        + " is similar to \"" + name + "\""
                        + " with score : " + getScore(idScore)
                ) + "\n";
            }
            return logString;
        }
        return logString;
    }

    private Boolean hasSimilarSpecies(Species sp) {
        String similarIds = getValue(sp, "similar to");
        return !similarIds.isEmpty();
    }

    public void findSimilarReaction(ArrayList<SBMLDocument> sbmlDocList) throws XMLStreamException {
        Model model1 = sbmlDocList.get(0).getModel();
        Model model2 = sbmlDocList.get(1).getModel();
        reactionMatchMatrix = new int[model1.getReactionCount()][model2.getReactionCount()];
        int i = 0, counter = 0;
        for (Reaction reaction1 : model1.getListOfReactions()) {
            int j = 0;
            for (Reaction reaction2 : model2.getListOfReactions()) {
                int score = 0;

                if (isEqual(reaction1.getId(), reaction2.getId())) {
                    score += 10;
                }

                // compare Reaction Names
                // isSimilar return a value between 0 and 1
                double nameSimilarityScore = isSimilar(reaction1.getName(), reaction2.getName());
                if (nameSimilarityScore >= nameSimilarityThreshold) {
                    score += 100;
                }

                score += compareSpeciesOfTwoReaction(reaction1, reaction2);  // 1000 or 0
//
//                if (reaction1.getId().equals(model1BiomassId) && reaction2.getId().equals(model2BiomassId) ||
//                        reaction1.getId().equals(model2BiomassId) && reaction2.getId().equals(model1BiomassId))
//                    score += 1000;
//
                for (String key : reactionInfoKeys) //TODO handle species which has keys with multiple values like "GENE ID : 01xXsd 23DCXed"
                {
                    if (isEqual(getValue(reaction1, key).split(" ")[0], getValue(reaction2, key).split(" ")[0])) {
                        score += 10000;
                    }
                }

                if (score >= similarityScoreThreshold) {
                    setValue(reaction1, "similar to", reaction2.getId() + "," + score);
                    setValue(reaction2, "similar to", reaction1.getId() + "," + score);
                    reactionMatchMatrix[i][j] = score;
                }
                j++;
            }
            ArrayList<SBMLDocument> otherDocs = new ArrayList<>(sbmlDocList);
            otherDocs.remove(0);
            printSimilarReactionOf(reaction1, otherDocs);
            if (hasSimilarReaction(reaction1)) // if species1 has similar species
            {
                counter++;
            }
            i++;
        }
        logger.info("\n\n"
                + "===================\n"
                + "number of similar reactions founded : " + counter + "\n"
                + "===================\n"
                + "\n");
    }

    public void findSimilarReaction(ArrayList<SBMLDocument> sbmlDocList, JTextArea txtArea, JProgressBar ProgressBar) {
        ProgressBar.setMinimum(0);
        Model model1 = sbmlDocList.get(0).getModel();
        Model model2 = sbmlDocList.get(1).getModel();
        ProgressBar.setMaximum(model1.getReactionCount());
        Document doc = txtArea.getDocument();
        reactionMatchMatrix = new int[model1.getReactionCount()][model2.getReactionCount()];
        int i = 0;
        long counter = 0;
        for (Reaction reaction1 : model1.getListOfReactions()) {
            int j = 0;
            for (Reaction reaction2 : model2.getListOfReactions()) {
                int score = 0;

                if (isEqual(reaction1.getId(), reaction2.getId())) {
                    score += 10;
                }

                // compare Reaction Names
                // isSimilar return a value between 0 and 1
                double nameSimilarityScore = isSimilar(reaction1.getName(), reaction2.getName());
                if (nameSimilarityScore >= nameSimilarityThreshold) {
                    score += 100;
                }

                try {
                    score += compareSpeciesOfTwoReaction(reaction1, reaction2);  // 1000 or 0

                } catch (XMLStreamException ex) {
                    Logger.getLogger(MergeSBML.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
//
//                if (reaction1.getId().equals(model1BiomassId) && reaction2.getId().equals(model2BiomassId) ||
//                        reaction1.getId().equals(model2BiomassId) && reaction2.getId().equals(model1BiomassId))
//                    score += 1000;
//
                for (String key : reactionInfoKeys) //TODO handle species which has keys with multiple values like "GENE ID : 01xXsd 23DCXed"
                {
                    if (isEqual(getValue(reaction1, key).split(" ")[0], getValue(reaction2, key).split(" ")[0])) {
                        score += 10000;
                    }
                }

                if (score >= similarityScoreThreshold) {
                    setValue(reaction1, "similar to", reaction2.getId() + "," + score);
                    setValue(reaction2, "similar to", reaction1.getId() + "," + score);
                    reactionMatchMatrix[i][j] = score;
                }
                j++;
            }
            ArrayList<SBMLDocument> otherDocs = new ArrayList<>(sbmlDocList);
            otherDocs.remove(0);
            String log = printSimilarReactionOf(reaction1, otherDocs);
            try {
                if (!log.trim().equals("")) {
                    doc.insertString(doc.getLength(), log, null);
                }
            } catch (BadLocationException ex) {
                logger.info(ex.getMessage());
            }
            if (hasSimilarReaction(reaction1)) // if species1 has similar species
            {
                counter++;
            }
            ProgressBar.setValue(++i);
        }
        String log = logger.info("\n\n"
                + "===================\n"
                + "number of similar reactions founded : " + counter + "\n"
                + "===================\n"
                + "\n");
        parentFrame.setNumOfSimilarReactions(counter);
        try {
            if (!log.equals("")) {
                doc.insertString(doc.getLength(), log, null);
            }
        } catch (BadLocationException ex) {
            logger.info(ex.getMessage());
        }
    }

    private String printSimilarReactionOf(Reaction re, ArrayList<SBMLDocument> otherDocList) {
        String similarIds = getValue(re, "similar to");
        String logString = "";
        Reaction reaction = null;
        if (!similarIds.isEmpty()) {
            String[] idScores = similarIds.split(" ");
            logString += logger.info("Reaction \"" + re.getId() + "\" : " + printReaction(re)) + "\n";
            logString += logger.info("================================*** similar to ***===================================") + "\n";
            for (String idScore : idScores) {
                for (SBMLDocument doc : otherDocList) {
                    if (doc.getModel().getReaction(getId(idScore)) != null) {
                        reaction = doc.getModel().getReaction(getId(idScore));
                        break;
                    }
                }
                if (reaction != null) {
                    logString += logger.info("Reaction \"" + reaction.getId() + "\" : " + printReaction(reaction)) + "\n";
                    logString += logger.info("similarity score: " + getScore(idScore)) + "\n";
                }
            }
            logString += logger.info("\n\n");
            return logString;
        }
        return logString;
    }

    private Boolean hasSimilarReaction(Reaction re) {
        String similarIds = getValue(re, "similar to");
        return !similarIds.isEmpty();
    }

    private String printReaction(Reaction re) {
        String out = "";
        for (int i = 0; i < re.getListOfReactants().size(); i++) {
            if (i != 0) {
                out += " + ";
            }
            out += ("\"" + (int) re.getReactant(i).getStoichiometry()) + " " + re.getReactant(i).getSpeciesInstance().getName() + "\"";
        }
        String direction = " <=> ";
        if (re.isSetReversible()) {
            if (!re.getReversible()) {
                direction = " => ";
            }
        }
        out += direction;
        for (int i = 0; i < re.getListOfProducts().size(); i++) {
            if (i != 0) {
                out += " + ";
            }
            out += ("\"" + (int) re.getProduct(i).getStoichiometry()) + " " + re.getProduct(i).getSpeciesInstance().getName() + "\"";
        }
        return out;
    }

    private double compareSpeciesOfTwoReaction(Reaction reaction1, Reaction reaction2) throws XMLStreamException {
        double score = 0;
        double scoreRevers = 0;
        ArrayList<ImmutableTriple<String, String, Integer>> matchedSpecies
                = new ArrayList<>();
        //if reaction.reversibility is not set, it's default value is True.
        boolean bothAreNotReversible = false;

        // if reaction's reversibility are different, they must not match.
        if (!reaction1.isSetReversible()) {
            reaction1.setReversible(true);
        }

        if (!reaction2.isSetReversible()) {
            reaction2.setReversible(true);
        }

        if (reaction1.isReversible() == false && reaction2.isReversible() == false) {
            bothAreNotReversible = true; // score = 0
        }
        score += getSimilarityScore(
                reaction1.getListOfReactants(),
                reaction2.getListOfReactants(),
                matchedSpecies
        );
        score += getSimilarityScore(
                reaction1.getListOfProducts(),
                reaction2.getListOfProducts(),
                matchedSpecies
        );

        if (!bothAreNotReversible) { // if at least one reaction is reversible , match reverse reaction.
            ArrayList<ImmutableTriple<String, String, Integer>> reverseMatchedSpecies
                    = new ArrayList<>();
            scoreRevers -= getSimilarityScore(
                    reaction1.getListOfReactants(),
                    reaction2.getListOfProducts(),
                    reverseMatchedSpecies
            );
            scoreRevers -= getSimilarityScore(
                    reaction1.getListOfProducts(),
                    reaction2.getListOfReactants(),
                    reverseMatchedSpecies
            );
            if ((-1 * scoreRevers) > score) {
                score = scoreRevers;
                matchedSpecies = reverseMatchedSpecies;
            }
        }

        if (score >= reactionSpeciesMatchThreshold || score <= -reactionSpeciesMatchThreshold) {
            for (ImmutableTriple<String, String, Integer> matchedSpecie : matchedSpecies) {
                Species sp1 = reaction1.getModel().getSpecies(matchedSpecie.getLeft());
                Species sp2 = reaction2.getModel().getSpecies(matchedSpecie.getMiddle());
                if (similarityScore(sp1, sp2) < 1000) {
                    insertBySort(getValue(sp1, "similar to"), sp2.getId() + "," + 1000);
                    insertBySort(getValue(sp2, "similar to"), sp1.getId() + "," + 1000);
                    logger.info("after matching two reaction \""
                            + reaction1.getName() + "\" and \"" + reaction2.getName() + "\" with score=" + score
                            + ", similarity socre of \"" + sp1.getName() + "\" and \"" + sp2.getName() + "\""
                            + " updated from " + similarityScore(sp1, sp2) + " to 1000");
                }
            }
        }
        return score;
    }

    private double getSimilarityScore(ListOf<SpeciesReference> list1,
            ListOf<SpeciesReference> list2,
            ArrayList<ImmutableTriple<String, String, Integer>> scores) {
        if (scores == null) {
            scores = new ArrayList<>();
        }

        ListOf<SpeciesReference> spList1 = list1.clone();
        ListOf<SpeciesReference> spList2 = list2.clone();

        while (spList1.size() > 0 || spList2.size() > 0) {
            int max = -1, row = -1, col = -1;

            // find two species with maximum match score
            for (int r = 0; r < spList1.size(); r++) {
                for (int c = 0; c < spList2.size(); c++) {

                    // if two species have not similar stoichiometry, they are not match
                    if (spList1.get(r).getStoichiometry() != spList2.get(c).getStoichiometry()) {
                        continue;
                    }

                    Species sp1 = list1.getModel().getSpecies(spList1.get(r).getSpecies());
                    Species sp2 = list2.getModel().getSpecies(spList2.get(c).getSpecies());
                    int score = 0;
                    if (max < (score = similarityScore(sp1, sp2))) {
                        max = score;
                        row = r;
                        col = c;
                    }
                }
            }

            // if maximum match score is 0 , so remained species does not match anymore, then break while
            if (max == 0 || max == -1) {
                break;
            }

            // add maximum match score which found, to scores list
            scores.add(
                    new ImmutableTriple<>(spList1.get(row).getSpecies(), spList2.get(col).getSpecies(), max)
            );

            // delete matched row and column
            spList1.remove(row);
            spList2.remove(col);
        }

        int allSpecies = list1.size() + list2.size();
        int unmatchedSpecies = spList1.size() + spList2.size();
        int sum = 0;
        for (ImmutableTriple<String, String, Integer> score : scores) {
            sum += score.getRight(); //getRight:getScore
        }

        if (unmatchedSpecies == 0) {
            return ((2 * sum) / (allSpecies * Math.pow(10, unmatchedSpecies)));
        } else {
            return 0;
        }
    }

    private int similarityScore(Reaction reaction1, Reaction reaction2) {
        try {
            String similarIdValues = getValue(reaction1, "similar to");
            // similarIdValues= C00009_cyt,11 C00008_cyt,1
            for (String idValue : similarIdValues.split(" ")) {
                // idValue= C00009_cyt,11
                String id = getId(idValue);
                if (id.toLowerCase().equals(reaction2.getId().toLowerCase())) {
                    return getScore(idValue);
                }
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return 0;
        }
    }

    private int similarityScore(Species species1, Species species2) {
        try {
            String similarIdValues = getValue(species1, "similar to");
            // similarIdValues= C00009_cyt,11 C00008_cyt,1
            for (String idValue : similarIdValues.split(" ")) {
                //idValue= C00009_cyt,11
                String id = getId(idValue);
                if (id.toLowerCase().equals(species2.getId().toLowerCase())) {
                    return getScore(idValue);
                }
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return 0;
        }
    }

    private boolean isExternalSpecies(Species species) {
        return species.getCompartmentInstance().getName().contains("ext")
                || species.getCompartmentInstance().getId().contains("ext");
    }

    private ArrayList<String> extractInformation(XMLNode noteNode) {
        ArrayList<String> stringArray = new ArrayList<>();

        if (noteNode == null) {
            return stringArray;
        }
        String noteStr = noteNode.getCharacters().trim();
        if (!noteStr.isEmpty()) {
            int length = noteStr.split(":").length;
            if (length > 1) {
                boolean keyAdded = false;
                for (String key : speciesInfoKeys) {
                    if (noteStr.toLowerCase().contains(key.toLowerCase())) {
                        stringArray.add(key.trim() + " : " + noteStr.split(":")[length - 1].trim());
                        keyAdded = true;
                        // we assume that there is just one key:value in each Note String so if found,
                        // don't check other keys in resource
                        break;
                    }
                }

                // if no key found by speciesInfoKeys
                // we need to check reactionKeys
                if (!keyAdded) {
                    for (String key : reactionInfoKeys) {
                        if (noteStr.toLowerCase().contains(key.toLowerCase())) {
                            stringArray.add(key.trim() + " : " + noteStr.split(":")[length - 1].trim());
                            // we assume that there is just one key:value in each Note String so if found,
                            // don't check other keys in resource
                            break;
                        }
                    }
                }
            }
            stringArray.add(noteStr);
        }

        for (int i = 0; i < noteNode.getChildCount(); i++) {
            stringArray.addAll(extractInformation(noteNode.getChild(i)));
        }

        return stringArray;
    }

    private ArrayList<String> extractInformation(Annotation annotation) {
        ArrayList<String> stringArray = new ArrayList<>();
        for (CVTerm cvTerm : annotation.getListOfCVTerms()) {
            for (String resource : cvTerm.getResources()) {
                int length = resource.split(":").length;
                if (length > 1) {
                    boolean keyAdded = false;
                    for (String key : speciesInfoKeys) {
                        if (resource.toLowerCase().contains(key)) {
                            stringArray.add(key.trim() + " : " + resource.split(":")[length - 1].trim());
                            keyAdded = true;
                            // we assume that there is just one key:value in resource so if found,
                            // don't check other keys in resource
                            break;
                        }
                    }

                    // if a value added by speciesInfoKeys
                    // it does not need to check reactionKeys any more
                    // so go to next resource
                    if (keyAdded) {
                        continue;
                    }

                    for (String key : reactionInfoKeys) {
                        if (resource.toLowerCase().contains(key)) {
                            // we assume that there is just one key:value in resource so if found,
                            // don't check other keys in resource
                            stringArray.add(key.trim() + " : " + resource.split(":")[length - 1].trim());
                        }
                    }
                }
                stringArray.add(resource);
            }
        }
        return stringArray;
    }

    private void standardizeNotes(Species species) {
        try {
            ArrayList<String> notesList = extractInformation(species.getNotes());
            ArrayList<String> annotatinoList = extractInformation(species.getAnnotation());

            String newNotes = "<notes>\n";
            newNotes += "\t\t<body xmlns=\"http://www.w3.org/1999/xhtml\">\n";

            for (String note : notesList) {
                if (!newNotes.contains(note.trim())) {
                    newNotes += "\t\t\t<p>" + StringEscapeUtils.escapeHtml3(note.trim()) + "</p>\n";
                }
            }

            for (String annotation : annotatinoList) {
                if (!newNotes.contains(annotation.trim())) {
                    newNotes += "\t\t\t<p>" + StringEscapeUtils.escapeHtml3(annotation.trim()) + "</p>\n";
                }
            }

            newNotes += "\t\t</body>\n";
            newNotes += "</notes>";
            String s = new String(newNotes.getBytes(), Charset.forName("UTF-8"));
            s = StringEscapeUtils.escapeHtml3(s);
            species.setNotes(s);
        } catch (Exception ex) {
            logger.info("warning: there are some errors in notes of " + species.getId());
        }
    }

    private void standardizeNotes(Reaction reaction) {
        try {
            Reaction r2 = null;

            ArrayList<String> notesList = extractInformation(reaction.getNotes());
            ArrayList<String> annotatinoList = extractInformation(reaction.getAnnotation());

            String newNotes = "<notes>\n";

            newNotes += "\t\t<body xmlns=\"http://www.w3.org/1999/xhtml\">\n";

            for (String note : notesList) {
                newNotes += "\t\t\t<p>" + StringEscapeUtils.escapeHtml3(note.trim()) + "</p>\n";
            }

            for (String annotation : annotatinoList) {
                newNotes += "\t\t\t<p>" + StringEscapeUtils.escapeHtml3(annotation.trim()) + "</p>\n";
            }

            newNotes += "\t\t</body>\n";
            newNotes += "</notes>";
            String s = new String(newNotes.getBytes(), Charset.forName("UTF-8"));
            reaction.setNotes(s);
        } catch (Exception ex) {
            logger.info("warning: there are some errors in notes of " + reaction.getId());
        }
    }

    private static String getValue(Species species, String key) {
        String out = "";
        if (species.getNotes() != null) {
            XMLNode body = species.getNotes().getChild(0);
            if (body != null) {
                for (XMLNode p : body.getChildElements("p", "")) {
                    String item = p.getChild(0).getCharacters();
                    if (item.toLowerCase().contains(key.toLowerCase())) {
                        if (item.split(":").length > 1) {
                            String value = item.split(":")[1].trim();
                            if (!out.contains(value)) {
                                out += value + " ";
                            }
                        }
                    }
                }
            }
        }
        return out.trim();
    }

    private int getBestSimilarScore(Species species) {
        try {
            XMLNode body = species.getNotes().getChild(0);
            for (XMLNode p : body.getChildElements("p", "")) {
                String item = p.getChild(0).getCharacters();
                if (item.toLowerCase().contains("similar to")) {
                    if (item.split(":").length > 1) {
                        String value = item.split(":")[1].trim();
                        String keyValue;
                        if (value.split(" ").length > 1) // if this key has multiple values, return the first one
                        {
                            keyValue = value.split((" "))[0];
                        } else {
                            keyValue = value;
                        }

                        return getScore(keyValue);
                    }
                }
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return 0;
        }
    }

    private String getBestSimilarId(Species species) {
        try {
            XMLNode body = species.getNotes().getChild(0);
            for (XMLNode p : body.getChildElements("p", "")) {
                String item = p.getChild(0).getCharacters();
                if (item.toLowerCase().contains("similar to")) {
                    if (item.split(":").length > 1) {
                        String value = item.split(":")[1].trim();
                        String keyValue;
                        if (value.split(" ").length > 1) // if this key has multiple values, return the first one
                        {
                            keyValue = value.split((" "))[0].trim();
                        } else {
                            keyValue = value.trim();
                        }

                        return getId(keyValue).trim();
                    }
                }
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return "";
        }
    }

    public static String[] getSimilarSpecies(Species sp) {
        String SimilarIds = getValue(sp, "similar to");
        if (SimilarIds.isEmpty()) {
            return null;
        }
        return SimilarIds.trim().split(" ");
    }

    private static String getValue(Reaction reaction, String key) {
        String out = "";
        if (reaction.getNotes() != null) {
            XMLNode body = reaction.getNotes().getChild(0);
            if (body != null) {
                for (XMLNode p : body.getChildElements("p", "")) {
                    String item = p.getChild(0).getCharacters();
                    if (item.toLowerCase().contains(key.toLowerCase())) {
                        if (item.split(":").length > 1) {
                            String value = item.split(":")[1].trim();
                            if (!out.contains(value)) {
                                out += value + " ";
                            }
                        }
                    }
                }
            }
        }
        return out.trim();
    }

    private int getBestSimilarScore(Reaction reaction) {
        try {
            XMLNode body = reaction.getNotes().getChild(0);
            for (XMLNode p : body.getChildElements("p", "")) {
                String item = p.getChild(0).getCharacters();
                if (item.toLowerCase().contains("similar to")) {
                    if (item.split(":").length > 1) {
                        String value = item.split(":")[1].trim();
                        String keyValue;

                        if (value.split(" ").length > 1) // if this key has multiple values, return the first one
                        {
                            keyValue = value.split((" "))[0];
                        } else {
                            keyValue = value;
                        }

                        return getScore(keyValue);
                    }
                }
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return 0;
        }
    }

    private String getBestSimilarId(Reaction reaction) {
        try {
            XMLNode body = reaction.getNotes().getChild(0);
            for (XMLNode p : body.getChildElements("p", "")) {
                String item = p.getChild(0).getCharacters();
                if (item.toLowerCase().contains("similar to")) {
                    if (item.split(":").length > 1) {
                        String value = item.split(":")[1].trim();
                        String keyValue;
                        if (value.split(" ").length > 1) // if this key has multiple values, return the first one
                        {
                            keyValue = value.split((" "))[0].trim();
                        } else {
                            keyValue = value.trim();
                        }
                        return getId(keyValue).trim();
                    }
                }
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return "";
        }
    }

    public static String[] getSimilarReaction(Reaction re) {
        String SimilarIds = getValue(re, "similar to");
        if (SimilarIds.isEmpty()) {
            return null;
        }
        return SimilarIds.trim().split(" ");
    }

    private static int setValue(Species species, String key, String value) {
        String prevValue;
        prevValue = getValue(species, key);
        XMLNode body = species.getNotes().getChild(0);
        if (!prevValue.trim().isEmpty()) { // there is a note with this key
            for (int i = 0; i < body.getChildCount(); i++) {
                try {
                    String keyValue = body.getChild(i).toXMLString();
                    if (keyValue.toLowerCase().contains(key.toLowerCase())) {
                        if (keyValue.toLowerCase().contains(value.toLowerCase())) {
                            // if this species already has this key and value, do nothing
                            return 0;
                        } else { // if it is a new value for this key
                            // if next child is "\n" remove it , and then remove founded node..
                            if (body.getChild(i + 1).getChildCount() == 0 && body.getChild(i + 1).getCharacters().equals("\n")) {
                                body.removeChild(i + 1);
                            }
                            body.removeChild(i);
                            break;
                        }
                    }
                } catch (XMLStreamException ex) {
                    ex.printStackTrace(System.out);
                }
            }
        }
        XMLNode pNode = new XMLNode();
        pNode.setTriple(new XMLTriple("p", body.getNamespaceURI(), ""));
        String newValue;
        if (key.equals("similar to")) {
            newValue = insertBySort(prevValue, value);
        } else {
            newValue = prevValue.trim() + " " + value.trim();
        }
        pNode.addChild(new XMLNode(key + " : " + newValue));
        body.addChild(pNode);
        // add a "\n" node after adding key:value node..
        body.addChild(new XMLNode("\n"));
        return 0;
    }

    private static int deleteValue(Species species, String key) {
        String prevValue;
        prevValue = getValue(species, key);
        if (species.getNotes() != null) {
            XMLNode body = species.getNotes().getChild(0);
            if (!prevValue.trim().isEmpty() && body != null) { // there is a note with this key
                for (int i = 0; i < body.getChildCount(); i++) {
                    try {
                        if (body.getChild(i).toXMLString().contains(key)) {
                            // if next child is "\n" remove it , and then remove founded node..
                            if (body.getChild(i + 1).getChildCount() == 0 && body.getChild(i + 1).getCharacters().equals("\n")) {
                                body.removeChild(i + 1);
                            }
                            body.removeChild(i);
                            break;
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace(System.out);
                        return -1;
                    }
                }
            }
        }
        return 0;
    }

    private static int setValue(Reaction reaction, String key, String value) {
        String prevValue;
        prevValue = getValue(reaction, key);
        XMLNode body = reaction.getNotes().getChild(0);
        if (!prevValue.isEmpty()) { // there is a note with this key
            for (int i = 0; i < body.getChildCount(); i++) {
                try {
                    String keyValue = body.getChild(i).toXMLString();
                    if (keyValue.toLowerCase().contains(key.toLowerCase())) {
                        if (keyValue.toLowerCase().contains(value.toLowerCase())) {
                            // if this species already has this key and value, do nothing
                            return 0;
                        } else { // if it is a new value for this key
                            // if next child is "\n" remove it , and then remove founded node..
                            if (body.getChild(i + 1).getChildCount() == 0 && body.getChild(i + 1).getCharacters().equals("\n")) {
                                body.removeChild(i + 1);
                            }
                            body.removeChild(i);
                            break;
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace(System.out);
                    return -1;
                }
            }
        }
        XMLNode pNode = new XMLNode();
        pNode.setTriple(new XMLTriple("p", body.getNamespaceURI(), ""));
        String newValue;
        if (key.equals("similar to")) {
            newValue = insertBySort(prevValue, value);
        } else {
            newValue = prevValue.trim() + " " + value.trim();
        }
        pNode.addChild(new XMLNode(key.trim() + " : " + newValue.trim()));
        body.addChild(pNode);
        // add a "\n" node after adding key:value node..
        body.addChild(new XMLNode("\n"));
        return 0;
    }

    private static int deleteValue(Reaction reaction, String key) {
        String prevValue;
        prevValue = getValue(reaction, key);
        if (reaction.getNotes() != null) {
            XMLNode body = reaction.getNotes().getChild(0);
            if (!prevValue.isEmpty() && body != null) { // there is a note with this key
                for (int i = 0; i < body.getChildCount(); i++) {
                    try {
                        if (body.getChild(i).toXMLString().contains(key)) {
                            // if next child is "\n" remove it , and then remove founded node..
                            if (body.getChild(i + 1).getChildCount() == 0 && body.getChild(i + 1).getCharacters().equals("\n")) {
                                body.removeChild(i + 1);
                            }
                            body.removeChild(i);
                            break;
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace(System.out);
                        return -1;
                    }
                }
            }
        }
        return 0;
    }

    public static String getId(String idScore) {
        try {
            return idScore.split(",")[0].trim();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return "";
        }
    }

    public static int getScore(String idScore) {
        try {
            return Integer.parseInt(idScore.split(",")[1].trim());
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return 0;
        }
    }

    private static String insertBySort(String values, String newKeyValue) {
        if (values.trim().isEmpty()) {
            return newKeyValue;
        }

        String[] valueArray = values.split(" ");
        String output = "";
        Boolean added = false;
        for (String keyValue : valueArray) {
            if (!added) {
                if (getScore(keyValue) <= getScore(newKeyValue)) {
                    output += newKeyValue.trim() + " ";
                    added = true;
                }
            }
            if (!getId(newKeyValue).equals(getId(keyValue))) // if there was a
            {
                output += keyValue.trim() + " ";
            }
        }
        return output.trim();
    }

    private double isSimilar(String str1, String str2) {
        if (str1.isEmpty() || str2.isEmpty()) {
            return 0;
        }
        double value = StringUtils.getJaroWinklerDistance(str1, str2);
//        logger.info("similarity of " + str1 + ", " + str2 + "= " + value);
        return value;
//        return str1.toLowerCase().contains(str2.toLowerCase()) || str2.toLowerCase().contains(str1.toLowerCase());
    }

    private boolean isEqual(String str1, String str2) {
        if (str1.isEmpty() || str2.isEmpty()) {
            return false;
        }

        return str1.toLowerCase().equals(str2.toLowerCase());

    }

    public String[] findBiomassCandidates(Model model) {
        ArrayList<String> biomassCandidateIds = new ArrayList<>();
        for (Reaction reaction : model.getListOfReactions()) {
            if (reaction.getName().toLowerCase().contains("biomass")
                    || reaction.getId().toLowerCase().contains("biomass")
                    || getValue(reaction, "gene").contains("biomass")) {
                biomassCandidateIds.add(reaction.getId());
            }
        }

        if (!biomassCandidateIds.isEmpty()) {
            String[] output = new String[biomassCandidateIds.size()];
            return biomassCandidateIds.toArray(output);
        }

        // if not find any reaction with "biomass" name, search in species,
        // find a spcies which name or id is biomass and
        // is product of one reaction and
        // is not reactant of any reaction
        for (Species species : model.getListOfSpecies()) {
            boolean itWasInReactants = false;
            if (species.getName().toLowerCase().contains("biomass")
                    || species.getId().toLowerCase().contains("biomass")) {
                // if species name is biomass, and it is just in product of a reaction, that reaction is biomass
                // if species name is biomass, and it is in reactant of a raction, that reaction is not biomass
                for (Reaction reaction : model.getListOfReactions()) {
                    for (SpeciesReference reactant : reaction.getListOfReactants()) {
                        if (reactant.getSpecies().equals(species.getId())) {
                            itWasInReactants = true;
                        }
                    }
                }

                if (!itWasInReactants) {
                    for (Reaction reaction : model.getListOfReactions()) {
                        for (SpeciesReference product : reaction.getListOfProducts()) {
                            if (product.getSpecies().equals(species.getId())) //if not duplicate , add it
                            {
                                if (biomassCandidateIds.indexOf(reaction.getId()) == -1) {
                                    biomassCandidateIds.add(reaction.getId());
                                }
                            }
                        }
                    }
                }
            }
        }

        if (!biomassCandidateIds.isEmpty()) {
            String[] output = new String[biomassCandidateIds.size()];
            return biomassCandidateIds.toArray(output);
        }

        // if not find any reaction with "biomass" name yet, search in species,
        // find a spcies which name or id is biomass and
        // is product of at least one reaction
        for (Species species : model.getListOfSpecies()) {
            if (species.getName().toLowerCase().contains("biomass")
                    || species.getId().toLowerCase().contains("biomass")) {
                for (Reaction reaction : model.getListOfReactions()) {
                    for (SpeciesReference product : reaction.getListOfProducts()) {
                        if (product.getSpecies().equals(species.getId())) //if not duplicate , add it
                        {
                            if (biomassCandidateIds.indexOf(reaction.getId()) == -1) {
                                biomassCandidateIds.add(reaction.getId());
                            }
                        }
                    }
                }
            }
        }

        if (biomassCandidateIds.isEmpty()) {
            return null;
        }

        String[] output = new String[biomassCandidateIds.size()];
        return biomassCandidateIds.toArray(output);
    }

    public String[] findBiomass(Model model1, Model model2) throws XMLStreamException {

        //find biomass candidate in model1
        String[] biomassCandidate1 = findBiomassCandidates(model1);

        //find biomass candidate in model2
        String[] biomassCandidate2 = findBiomassCandidates(model2);

        if (biomassCandidate1.length == 0 && biomassCandidate2.length != 0) {
            return new String[]{"", biomassCandidate2[0]};
        }

        if (biomassCandidate1.length != 0 && biomassCandidate2.length == 0) {
            return new String[]{biomassCandidate1[0], ""};
        }

        double score, max = 0;
        int row = 0, col = 0;
        for (int i = 0; i < biomassCandidate1.length; i++) {
            for (int j = 0; j < biomassCandidate2.length; j++) {
                Reaction reaction1 = model1.getReaction(biomassCandidate1[i]);
                Reaction reaction2 = model2.getReaction(biomassCandidate2[j]);
                if ((score = compareSpeciesOfTwoReaction(reaction1, reaction2)) > max) {
                    max = score;
                    row = i;
                    col = j;
                }
            }
        }
        if (max != 0) {
            return new String[]{biomassCandidate1[row], biomassCandidate2[col]};
        } else {
            return new String[]{biomassCandidate1[0], biomassCandidate2[0]};
        }
    }

    private String hash(ArrayList<SBMLDocument> sbmlDocList) {
        long result = 17;
        for (SBMLDocument doc : sbmlDocList) {
            String name = doc.getModel().getName();
            result = 37 * result + name.hashCode();
        }
        if (result < 0) {
            result = -result;
        }
        return String.valueOf(result);
    }

    public static Map<String, String> getInfoFromInternet(String name, boolean retry) {
        return getInfoFromInternet(name, "", retry);
    }

    public static Map<String, String> getInfoFromInternet(String name, String formula, boolean retry) {
        Map<String, String> map = new HashMap<>();
        String responseBodyForName;
        String responseBodyForFormula;
        String responseBodyForAll;
        HttpGet httpgetName;
        HttpGet httpgetFormula;
        HttpGet httpgetAll;
        String[] idsByName = null;
        String[] idsByFormula = null;
        String cpdId = "";
        name = refineName(name);
        CloseableHttpClient httpclient = HttpClients.createDefault();
//        System.out.println(httpclient.getParams().getParameter("http.protocol.expect-continue"));
//        RequestConfig requestConfig = RequestConfig.custom()
//                .setSocketTimeout(3000)
//                .setConnectTimeout(3000).build();
//        CloseableHttpClient httpclient = HttpClients.custom()
//                .setDefaultRequestConfig(requestConfig)
//                .setMaxConnPerRoute(1000)
//                .setMaxConnTotal(1000)
//                .build();
        try {
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                @Override
                public String handleResponse(
                        final HttpResponse response) throws IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };
            httpgetName = new HttpGet("http://rest.kegg.jp/find/compound/" + URLEncoder.encode(name.trim(), "UTF-8"));
//            logger.info("Executing request " + httpgetName.getRequestLine());
            responseBodyForName = httpclient.execute(httpgetName, responseHandler);
            if (responseBodyForName.length() > 1) {
//                logger.info(responseBodyForName);
                idsByName = parseResponse(responseBodyForName);
            }

            if (!formula.equals("")) {
                httpgetFormula = new HttpGet("http://rest.kegg.jp//find/compound/"
                        + URLEncoder.encode(formula.trim(), "UTF-8") + "/formula");
//                logger.info("Executing request " + httpgetName.getRequestLine());
                responseBodyForFormula = httpclient.execute(httpgetFormula, responseHandler);
                if (responseBodyForFormula.length() > 1) {
//                    logger.info(responseBodyForFormula);
                    idsByFormula = parseResponse(responseBodyForFormula);
                }
            }

            if (idsByName != null && idsByFormula != null) {
                cpdId = idsByFormula[0];
                for (String idByName : idsByName) {
                    for (String idByFormula : idsByFormula) {
                        if (idByName.equals(idByFormula)) {
                            cpdId = idByFormula;
                        }
                    }
                }
            } else if (idsByFormula != null) {
                cpdId = idsByFormula[0];
            } else if (idsByName != null) {
                for (int i = 0; i < responseBodyForName.split("\n").length; i++) {
                    String line = responseBodyForName.split("\n")[i];
                    line = ";" + line.substring(11);
                    for (String item : line.split(";")) {
                        if (item.trim().toLowerCase().equals(name.trim())) {
                            cpdId = idsByName[i];
                        }
                    }
                }
            }

            if (!cpdId.equals("")) {
                httpgetAll = new HttpGet("http://rest.kegg.jp/get/" + URLEncoder.encode(cpdId, "UTF-8"));
//                logger.info("Executing request " + httpgetAll.getRequestLine());
                responseBodyForAll = httpclient.execute(httpgetAll, responseHandler);
                if (responseBodyForAll.length() > 1) {
//                    logger.info(responseBodyForAll);
                    map = parseAll(responseBodyForAll);
                }
            }

            httpclient.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            // if there is a problem in fetch data, retry one more time..
            if (retry) {
                map = getInfoFromInternet(name, formula, false);
            }
        }
        return map;
    }

    private static String[] parseResponse(String response) {
        String[] cpdIds = null;
        if (response.length() != 1) {
            cpdIds = new String[response.split("\n").length];
            int i = 0;
            for (String line : response.split("\n")) {
                cpdIds[i++] = line.substring(0, 10);
            }
        }
        return cpdIds;
    }

    private static Map<String, String> parseAll(String response) {
        Map<String, String> map = null;
        if (response.length() > 1) {
            map = new HashMap<>();
            for (String line : response.split("\n")) {
                if (line.toLowerCase().contains("entry")) {
                    map.put("kegg", line.substring(line.indexOf(" "), line.lastIndexOf(" ")).trim());
                }

                if (line.toLowerCase().contains("formula")) {
                    map.put("formula", line.substring(line.lastIndexOf(" ")).trim());
                }

                if (line.toLowerCase().contains("pubchem:")) {
                    map.put("pubchem", line.split(":")[1].trim());
                }

                if (line.toLowerCase().contains("chebi:")) {
                    map.put("chebi", line.split(":")[1].trim());
                }
            }
        }
        return map;
    }

    private static String refineName(String name) {
        int index = name.toLowerCase().indexOf("_");
        if (index != -1) {
            name = name.toLowerCase().substring(0, index);
        }
        return name;
    }

    public String getBiomassId2() {
        return biomassId2;
    }

    public void setBiomassId2(String biomassId2) {
        this.biomassId2 = biomassId2;
    }

    public String getBiomassId1() {
        return biomassId1;
    }

    public void setBiomassId1(String biomassId1) {
        this.biomassId1 = biomassId1;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public int getSimilarityScoreThreshold() {
        return similarityScoreThreshold;
    }

    public void setSimilarityScoreThreshold(int similarityScoreThreshold) {
        this.similarityScoreThreshold = similarityScoreThreshold;
    }

    public double getNameSimilarityThreshold() {
        return nameSimilarityThreshold;
    }

    public void setNameSimilarityThreshold(double nameSimilarityThreshold) {
        this.nameSimilarityThreshold = nameSimilarityThreshold;
    }

    public double getCompartmentSimilarityThreshold() {
        return compartmentSimilarityThreshold;
    }

    public void setCompartmentSimilarityThreshold(double compartmentSimilarityThreshold) {
        this.compartmentSimilarityThreshold = compartmentSimilarityThreshold;
    }

    public double getReactionSpeciesMatchThreshold() {
        return reactionSpeciesMatchThreshold;
    }

    public void setReactionSpeciesMatchThreshold(double reactionSpeciesMatchThreshold) {
        this.reactionSpeciesMatchThreshold = reactionSpeciesMatchThreshold;
    }

    public String getNewSpeciesIDPrefix() {
        return newSpeciesIDPrefix;
    }

    public void setNewSpeciesIDPrefix(String newSpeciesIDPrefix) {
        this.newSpeciesIDPrefix = newSpeciesIDPrefix;
    }

    public String getNewReactionIDPrefix() {
        return newReactionIDPrefix;
    }

    public void setNewReactionIDPrefix(String newReactionIDPrefix) {
        this.newReactionIDPrefix = newReactionIDPrefix;
    }

    public String getNewFunctionIDPrefix() {
        return newFunctionIDPrefix;
    }

    public void setNewFunctionIDPrefix(String newFunctionIDPrefix) {
        this.newFunctionIDPrefix = newFunctionIDPrefix;
    }

    public String getNewCompartmentIDPrefix() {
        return newCompartmentIDPrefix;
    }

    public void setNewCompartmentIDPrefix(String newCompartmentIDPrefix) {
        this.newCompartmentIDPrefix = newCompartmentIDPrefix;
    }

    public int getLastInternetFetchedIndex() {
        return lastInternetFetchedIndex;
    }

    public void setLastInternetFetchedIndex(int lastInternetFetchedIndex) {
        this.lastInternetFetchedIndex = lastInternetFetchedIndex;
    }
}
