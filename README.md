SocialMediaMining
=================

A generic toolkit to mine social media (e.g. email, Facebook, Yahoo Answers)

About
Social media mining toolkit by Prasun Dewan and Jinjing Ma.
SoMMinT is a Java-based package for end-to-end social media mining process from processing raw social media data to generate prediction model or correlation analysis. It integrates a set of source data and general machine-learning toolkits such as Weka and Mallet, and researchers can switch data source and retarget the toolkits easily. Moreover, researchers can extract features in layers. 
	
Quick Start with Eclipse
The following is a guide to getting up an running as quickly as possible. The only assumption this quick start guide makes is that you have Eclipse installed.

Step 1: Download SoMMinT
Download link is https://github.com/pdewan/SocialMediaMining, add project to Eclipse.
Step 2: Get Weka and Mallet
Download Weka from http://www.cs.waikato.ac.nz/ml/weka/ and Mallet from http://mallet.cs.umass.edu/. Import Weka and Mallet as jar files or projects in SoMMinT project

Step 3: Get and Initialize the Repository
Clone the repository.
git clone https://github.com/jwbartel/Grader.git
From a command line (if using Windows, use command prompt not powershell), navigate to the folder you just cloned and run the following command.
mvn install:install-file -Dfile=oeall-22.jar -DgroupId=edu.unc -DartifactId=oeall -Dversion=22 -Dpackaging=jar
This adds the Object Editor jar file to your local Maven repository so that the dependency can be resolved.

Step 4: Run the Program
That's it, you're all set up. The default entry point is graderTools.Driver. You can run this file to run the grading tool.

Features
Please see SNMLToolkit/doc/SoMMinTDeveloperGuide.pdf and the project report.

Examples
Please refer the two examples on how to use the framework under the usercase package. 
