package runner;
 

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



@CucumberOptions(features =  "src/test/resources/features/", 
glue= {"selenium_glue_code"}, 
plugin = {"json:Results/cucumberjson/cucumber.json"})

public class CucumberRunnerTest {
	
	
	    
		private TestNGCucumberRunner testNGCucumberRunner;
	 
	    @BeforeClass(alwaysRun = true)
	    public void setUpClass() throws Exception {
	        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	    }
	 
	    
	    //groups = "cucumber", description = "Runs Cucumber Feature", 
	    
	    @Test(dataProvider = "features")
	    public void scenario(PickleWrapper pickle, FeatureWrapper cucumberFeature) {
	    	if (testNGCucumberRunner == null){
	    		System.out.println("runner is returning null: runscenario");
	    		
	    	}
	    	else{
	    		System.out.println("Runner is not null: runscenario");
	    	}
	        testNGCucumberRunner.runScenario(pickle.getPickle());
	    }

	    //
	    @DataProvider(name = "features")
	    public Object[][] scenarios() {
	    	if (testNGCucumberRunner == null){
	    		System.out.println("runner is returning null: dataprovider");
	    		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	    	}
	    	else{
	    		System.out.println("Runner is not null: dataprovider");
	    	
	    	}
	        return testNGCucumberRunner.provideScenarios();
	        
	    }
	 
	    
	 
	    @AfterClass(alwaysRun = true)
	    public void tearDownClass() throws Exception {
	    	try{
	    	testNGCucumberRunner.finish();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    }
	
	

}
