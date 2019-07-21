package careercup;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.plaf.basic.BasicTreeUI;

import trees.BinarySearchTree;

public class Questions {

	/**
	 * @brief Returns the list of all prime numbers less than num.
	 * <p> Algo:
	 * 	Sieve of Eratosthenes:
	 * 	list down all the numbers from 2 till Sqrt(num).
	 * 	starting 2 cross out all the numbers starting i^2, i^2+i, i^2+2i, and so on until value less than or equal num.
	 * 	Break when the loop reaches num.
	 * 	All the remaining numbers are prime numbers.
	 * </p>
	 * @param num
	 * @return
	 */
	public static List<Integer> getAllPrimeLessThan(int num) {
		List<Integer> numbers = IntStream.range(2, num + 1).boxed().collect(Collectors.toList());
		List<Boolean> flags = new ArrayList<>(numbers.size());
		for(int i = 0; i < numbers.size(); ++i) {
			flags.add(true);
		}
//		Collections.fill(flags, true);
		
		for(int i = 0; i < (int)Math.sqrt(numbers.size()); ++i) { // Here we need to iterate till sqrt of num 
			if(flags.get(i) == true) { // Flag set to true, so numbers not crossed out
				for(int j = (int)Math.pow(numbers.get(i),2)-2; j < numbers.size(); j+=numbers.get(i)) {
					flags.set(j, false);
				}
			}
		}
		
		List<Integer> retVal = new ArrayList<>();

		for(int i = 0; i < flags.size(); ++i) {
			if(flags.get(i) == true) {
				retVal.add(numbers.get(i));
			}
		}
		
		return retVal;
	}
	
	
	public void ques3() {
		BinarySearchTree bTree = new BinarySearchTree();
		bTree.addData(1);
	}
	
	public static void main(String[] args) {
		List<Integer> var = getAllPrimeLessThan(122);
		System.out.println(var);
	}

}
