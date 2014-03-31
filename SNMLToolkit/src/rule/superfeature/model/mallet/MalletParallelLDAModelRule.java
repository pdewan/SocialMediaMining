package rule.superfeature.model.mallet;

import java.io.File;

import cc.mallet.topics.ParallelTopicModel;
import cc.mallet.topics.TopicInferencer;
import cc.mallet.types.Instance;
import cc.mallet.types.InstanceList;
import dataconvert.IntermediateData;
import dataconvert.IntermediateDataSet;

public class MalletParallelLDAModelRule  extends MalletTopicModelRule implements IMalletModelRule{

	public MalletParallelLDAModelRule(String featureName, String[] attrNames,
			int l) {
		super(featureName, attrNames, l);
	}

	ParallelTopicModel model;
	

	@Override
	public void train(IntermediateDataSet trainingSet, String[] options)
			throws Exception {
		
		InstanceList instances = this.convert(trainingSet);
		
		// Create a model with length topics, alpha_t = 0.01, beta_w = 0.01
        //  Note that the first parameter is passed as the sum over topics, while
        //  the second is the parameter for a single dimension of the Dirichlet prior.
        int numTopics = this.length;
        model = new ParallelTopicModel(numTopics, 1.0, 0.01);

        model.addInstances(instances);

        // Use two parallel samplers, which each look at one half the corpus and combine
        //  statistics after every iteration.
        model.setNumThreads(2);

        // Run the model for 50 iterations and stop (this is for testing only, 
        //  for real applications, use 1000 to 2000 iterations)
        model.setNumIterations(50);
        model.estimate();
        
        
	}

	@Override
	public void save(String modelFilePath) throws Exception {
		File file = new File(modelFilePath);
		if(!file.exists()){
			file.createNewFile();
		}
		model.write(file);		
	}
	
	

	@Override
	public void load(String modelFilePath) throws Exception {
		File file = new File(modelFilePath);	
		model = ParallelTopicModel.read(file);
	}

	@Override
	public Object extract(IntermediateData anInstData) throws Exception {

		Instance inst = this.convert(anInstData);
		
        // Estimate the topic distribution of instance, 
        //  given the current Gibbs state.	
        TopicInferencer inferencer = model.getInferencer();
        double[] testProbabilities = inferencer.getSampledDistribution(inst, 10, 1, 5);
		
        return testProbabilities;
	}


}
