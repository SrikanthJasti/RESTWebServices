import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;


public class TestResults {

	@SuppressWarnings("resource")
	public static void main(String args[]){
	
		Set<Integer> test = new HashSet<Integer>();
		
		test.add(1);
		test.add(1);
		test.add(2);
		for(Integer i : test){
			System.out.println(i);
		}
		try {
			FileReader reader = new FileReader("C:\\Users\\srikanth\\Downloads\\rama2.log");
			BufferedReader br = new BufferedReader(reader);
			File file = new File("C://Users//srikanth//Downloads//filename.txt");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			String str;
			String y = "TestStartRequest";
			String q = "TestEndRequest";
			String x = "ERROR";
			String w = "FAIL";
			String z = "INCONCLUSIVE";
			ArrayList<String> input = new ArrayList<String>();
			ArrayList<String> output = new ArrayList<String>();
			Set<String> observations = new HashSet<String>();
			while((str=br.readLine())!=null){
				if(str.contains(y)){
					input.add(str);
				}
				if(str.contains(q)){
					output.add(str);
				}
			}
			System.out.println(input.size()+" "+output.size());
			for(int j=0;j<output.size();j++){
				String[] output_split = output.get(j).split(",");
				observations.add(output_split[2]);
			}
			TreeMap<Integer,String> tree_map =
					new TreeMap<Integer, String>();
			HashMap<String,HashMap<Integer,HashMap<String,Set<String>>>> map =
					new HashMap<String, HashMap<Integer,HashMap<String,Set<String>>>>();
	
			for(String obs : observations){
				int count = 0;
				HashMap<Integer,HashMap<String,Set<String>>> inner_map1 =
						new HashMap<Integer,HashMap<String,Set<String>>>();
				HashMap<String,Set<String>> inner_map2 =
						new HashMap<String, Set<String>>();
				Set<String> testNamesError = new HashSet<String>();
				Set<String> testNamesFail = new HashSet<String>();
				Set<String> testNamesInconclusive = new HashSet<String>();
				for(int i=0;i<output.size();i++){
					
					if(output.get(i).contains(obs)){
						count+=1;
					}
					if(output.get(i).contains(x) && output.get(i).contains(obs)){
						String[] input_split = input.get(i).split(",");
						testNamesError.add(input_split[1]);
					}
					if(output.get(i).contains(w) && output.get(i).contains(obs)){
						String[] input_split = input.get(i).split(",");
						testNamesFail.add(input_split[1]);
					}
					if(output.get(i).contains(z) && output.get(i).contains(obs)){
						String[] input_split = input.get(i).split(",");
						testNamesInconclusive.add(input_split[1]);
					}
				}
				inner_map2.put(x, testNamesError);
				inner_map2.put(w, testNamesFail);
				inner_map2.put(z, testNamesInconclusive);
				inner_map1.put(count, inner_map2);
				map.put(obs, inner_map1);
				tree_map.put(count, obs);
			}
			
			for(Integer i : tree_map.descendingKeySet()){
				System.out.println(tree_map.get(i)+"--->"+i);
				bw.write(tree_map.get(i)+"--->"+i);
				bw.newLine();
				String s = tree_map.get(i);
				HashMap<Integer,HashMap<String,Set<String>>> map1 = 
						map.get(s);
				HashMap<String,Set<String>> map2 = map1.get(i);
				for(String keys : map2.keySet()){
					Set<String> list = map2.get(keys);
					for(String st : list){
						bw.write(keys+": \t"+st);
						bw.newLine();
					}
					System.out.println(keys +"-->"+map2.get(keys));
				}
			}
			
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
