package rule.superfeature.model.mallet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.regex.Pattern;

import rule.superfeature.model.NumericVectorModelRule;
import cc.mallet.pipe.CharSequence2TokenSequence;
import cc.mallet.pipe.Input2CharSequence;
import cc.mallet.pipe.Pipe;
import cc.mallet.pipe.PrintInputAndTarget;
import cc.mallet.pipe.SerialPipes;
import cc.mallet.pipe.Target2Label;
import cc.mallet.pipe.TokenSequence2FeatureSequence;
import cc.mallet.pipe.TokenSequenceLowercase;
import cc.mallet.pipe.TokenSequenceRemoveStopwords;
import cc.mallet.pipe.iterator.CsvIterator;
import cc.mallet.types.Instance;
import cc.mallet.types.InstanceList;
import dataconvert.IntermediateData;
import dataconvert.IntermediateDataSet;

public abstract class MalletTopicModelRule extends NumericVectorModelRule
		implements IMalletModelRule {

	String[] attrNames;
	Pipe pipe;
	
	public MalletTopicModelRule(String featureName, String[] attrNames, int l) {
		super(featureName, l);
		this.attrNames = attrNames;
		pipe = buildPipe();
	}
	
	protected String saveToMalletFormat(IntermediateDataSet trainingSet)
			throws Exception {
		
		String path = "malletTopicModelRuleTmp.txt";
		BufferedWriter writer = new BufferedWriter(new FileWriter(path));
		for(int instId=1; instId<trainingSet.size(); instId++){
			StringBuilder sb = new StringBuilder();
			sb.append(instId).append('\t').append('X').append('\t');
			
			IntermediateData data = trainingSet.getDataInstance(instId);
			for(int i=0; i<attrNames.length; i++){
				sb.append(data.getStringAttrValue(attrNames[i]).replace('\n', ' '));
			}
			sb.append('\n');
			writer.write(sb.toString());
		}
		writer.flush();
		writer.close();
		return path;
	}
	
	protected String saveToMalletFormat(IntermediateData data)
			throws Exception {
		
		String path = "malletTopicModelRuleTmp.txt";
		BufferedWriter writer = new BufferedWriter(new FileWriter(path));
		StringBuilder sb = new StringBuilder();
		sb.append(0).append('\t').append('X').append('\t');
					
		for(int i=0; i<attrNames.length; i++){
			sb.append(data.getStringAttrValue(attrNames[i]).replace('\n', ' '));
		}
		sb.append('\n');
		writer.write(sb.toString());
		
		writer.flush();
		writer.close();
		return path;
	}
	
	protected InstanceList convert(IntermediateDataSet dataset)
			throws Exception{
		
		String tmpFilePath = saveToMalletFormat(dataset);
		File tmpFile = new File(tmpFilePath);
		
		
		InstanceList instances = new InstanceList(pipe);
		
		Reader fileReader = new InputStreamReader(new FileInputStream(new File(tmpFilePath)), "UTF-8");
        instances.addThruPipe(new CsvIterator (fileReader, Pattern.compile("^(\\S*)[\\s,]*(\\S*)[\\s,]*(.*)$"),
                                               3, 2, 1)); // data, label, name fields
        tmpFile.delete();
        
        return instances;
	}
	
	protected Instance convert(IntermediateData data)
			throws Exception{
		
		String tmpFilePath = saveToMalletFormat(data);
		File tmpFile = new File(tmpFilePath);
		
		Pipe pipe = buildPipe();
		InstanceList instances = new InstanceList(pipe);
		
		Reader fileReader = new InputStreamReader(new FileInputStream(new File(tmpFilePath)), "UTF-8");
        instances.addThruPipe(new CsvIterator (fileReader, Pattern.compile("^(\\S*)[\\s,]*(\\S*)[\\s,]*(.*)$"),
                                               3, 2, 1)); // data, label, name fields
        tmpFile.delete();
        
        return instances.get(0);
	}

	protected Pipe buildPipe() {
		ArrayList pipeList = new ArrayList();

        // Read data from File objects
        pipeList.add(new Input2CharSequence("UTF-8"));

        // Regular expression for what constitutes a token.
        //  This pattern includes Unicode letters, Unicode numbers, 
        //   and the underscore character. Alternatives:
        //    "\\S+"   (anything not whitespace)
        //    "\\w+"    ( A-Z, a-z, 0-9, _ )
        //    "[\\p{L}\\p{N}_]+|[\\p{P}]+"   (a group of only letters and numbers OR
        //                                    a group of only punctuation marks)
        Pattern tokenPattern =
            Pattern.compile("[\\p{L}\\p{N}_]+");

        // Tokenize raw strings
        pipeList.add(new CharSequence2TokenSequence(tokenPattern));

        // Normalize all tokens to all lowercase
        pipeList.add(new TokenSequenceLowercase());

        // Remove stopwords from a standard English stoplist.
        //  options: [case sensitive] [mark deletions]
        pipeList.add(new TokenSequenceRemoveStopwords(false, false));

        // Rather than storing tokens as strings, convert 
        //  them to integers by looking them up in an alphabet.
        pipeList.add(new TokenSequence2FeatureSequence());

        // Do the same thing for the "target" field: 
        //  convert a class label string to a Label object,
        //  which has an index in a Label alphabet.
        pipeList.add(new Target2Label());

        // Now convert the sequence of features to a sparse vector,
        //  mapping feature IDs to counts.
        //pipeList.add(new FeatureSequence2FeatureVector());

        // Print out the features and the label
        pipeList.add(new PrintInputAndTarget());

        return new SerialPipes(pipeList);
		
	}

	
}
