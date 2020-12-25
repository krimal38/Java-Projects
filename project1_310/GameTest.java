import java.io.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class GameTest {
	private static final String sampleDirectory = "./sampleOuts/";
	private static final String newline = System.getProperty("line.separator");
	private static ByteArrayOutputStream localOut, localErr;
	private static PrintStream sysOut, sysErr;
	
	//set the print streams we will be using
	@BeforeClass
	public static void setup() throws Exception {
		sysOut = System.out;
		sysErr = System.err;
	}

	//before every test is run, reset the streams to capture stdout/stderr
	@Before
	public void setupStreams() {
		localOut = new ByteArrayOutputStream();
		localErr = new ByteArrayOutputStream();
		System.setOut(new PrintStream(localOut));
		System.setErr(new PrintStream(localErr));
	}

	//after every test, restore stdout/stderr
	@After
	public void cleanUpStreams() {
		System.setOut(null);
		System.setErr(null);
		System.setOut(sysOut);
		System.setErr(sysErr);
	}
	
	private ArrayList<String> getFileContents(String fileName) {
		//run test (simulate user input)
		try(Scanner s = new Scanner(new File(fileName))) {
			ArrayList<String> a = new ArrayList<>();
			while(s.hasNextLine()) a.add(s.nextLine().trim());
			return a;
		}
		catch(FileNotFoundException e) {
			fail("Could not find " + sampleDirectory + fileName);
		}
		return null;
	}
	
	public void runTest(String inputFile, String outputFile, String[] mainArgs) {
		//setup (simulate user input)
		StringBuffer sb = new StringBuffer();
		for(String s : getFileContents(sampleDirectory + inputFile)) {
			sb.append(s);
			sb.append(newline);
		}
		System.setIn(new ByteArrayInputStream(sb.toString().getBytes()));
		
		//run test
		GoblinGame.main(mainArgs);
		String[] output = getFileContents(sampleDirectory + outputFile).toArray(new String[0]);
		
		//check output
		String[] lines = localOut.toString().split("\n");
		for(int i = 0; i < output.length; i++) {
			if(i >= lines.length) {
				fail("Missing line:\n\"" + output[i] + "\"");
			}
			assertTrue("Line " + (i+1) + " incorrect.\nExpected:\n"+output[i]+"\nGot:\n"+lines[i], lines[i].replaceAll("\\s","").equals(output[i].replaceAll("\\s","")));
		}
		if(lines.length != output.length) {
			fail("Extra line:\n\"" + lines[output.length] + "\"");
		}
	}
	
	@Test(timeout = 2000)
	public void gameTest1() {
		String inputFile = "test1in.txt";
		String outputFile = "test1out-correct.txt";
		String[] mainArgs = {"dictionary-mini.txt", "6", "2"};
		runTest(inputFile, outputFile, mainArgs);
	}
	
	@Test(timeout = 2000)
	public void gameTest2() {
		String inputFile = "test1in.txt";
		String outputFile = "test1out-debug-correct.txt";
		String[] mainArgs = {"dictionary-mini.txt", "6", "2", "debug"};
		runTest(inputFile, outputFile, mainArgs);
	}
	
	@Test(timeout = 2000)
	public void gameTest3() {
		String inputFile = "test2in.txt";
		String outputFile = "test2out-correct.txt";
		String[] mainArgs = {"dictionary-mini.txt", "6", "2"};
		runTest(inputFile, outputFile, mainArgs);
	}
	
	@Test(timeout = 2000)
	public void gameTest4() {
		String inputFile = "test2in.txt";
		String outputFile = "test2out-debug-correct.txt";
		String[] mainArgs = {"dictionary-mini.txt", "6", "2", "debug"};
		runTest(inputFile, outputFile, mainArgs);
	}
	
	@Test(timeout = 5000)
	public void gameTest5() {
		String inputFile = "test3in.txt";
		String outputFile = "test3out-correct.txt";
		String[] mainArgs = {"dictionary.txt", "5", "100"};
		runTest(inputFile, outputFile, mainArgs);
	}
	
	@Test(timeout = 5000)
	public void gameTest6() {
		String inputFile = "test3in.txt";
		String outputFile = "test3out-debug-correct.txt";
		String[] mainArgs = {"dictionary.txt", "5", "100", "debug"};
		runTest(inputFile, outputFile, mainArgs);
	}
	
	public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("GameTest");
    }
}
