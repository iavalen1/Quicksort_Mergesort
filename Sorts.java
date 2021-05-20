/**
 * CSE 205: 17556 / M W 4:35 PM - 5:50 PM Assignment: Assingment #6 Author: Ivan
 * Valenzuela 1212575947 Description: This class has the two major recursive sorting
 * algorithms that I'll be using. It uses quicksort and mergeort.
 */
public class Sorts {

	public static void quicksort(final int[] aList) {
		final int pivotValue = aList[aList.length - 1];
		int pivotIndex = aList.length - 1;
		int storeIndex = 0;
		int compareIndex = 0;

		if (aList.length > 1) { // Handles edge case when only one element in list

			/**
			 * This creates the smaller left values and greater right values
			 */
			while (compareIndex < pivotIndex) {
				final boolean pivotIsGreater = pivotValue >= aList[compareIndex];

				if (pivotIsGreater) {
					swapValuesInList(aList, compareIndex, storeIndex);
					storeIndex++;
				}

				compareIndex++;
			}

			swapValuesInList(aList, pivotIndex, storeIndex); // Swaps the pivot index and store index

			/**
			 * Swaps the index variables for pivot and store since they will be used later
			 */
			final int placeHolder = pivotIndex;

			pivotIndex = storeIndex;
			storeIndex = placeHolder;

			/**
			 * Handles edge cases when there are no smaller values than pivot
			 */
			if (pivotIndex > 0) {
				final int[] leftList = makeListFromIndex(aList, 0, pivotIndex - 1); // Creates list for the
				// left side
				quicksort(leftList); // recursive call on the left list
				overWriteList(aList, leftList, 0, pivotIndex - 1); // Overwrites the left side of original list with
				// sorted leftList
			}

			/**
			 * Handles edge cases when there are no larger values than pivot
			 */
			if (pivotIndex < aList.length - 1) {
				// Creates list for the right side
				final int[] rightList = makeListFromIndex(aList, pivotIndex + 1, aList.length - 1);
				quicksort(rightList); // recursive call on the right list
				// Overwrites the right side of the original list with the sorted rightList
				overWriteList(aList, rightList, pivotIndex + 1, aList.length - 1);
			}

		}

	}

	/**
	 * This is the method mergesort which uses the mergesort sorting algorithm. This
	 * method splits the list in half and makes a recursive call on both lists until
	 * there's only one element in every list. The left and right lists are then
	 * compared and put back together in order.
	 */
	public static void mergesort(final int[] aList) {
		final int listSize = aList.length;
		final int finalIndex = listSize - 1;
		final int middleIndex = finalIndex / 2;

		// Creates the left and right list using the makeListFromIndex method
		final int[] leftList = makeListFromIndex(aList, 0, middleIndex);
		final int[] rightList = makeListFromIndex(aList, middleIndex + 1, finalIndex);

		/**
		 * This is where the lists are sorted and put back together
		 */
		if (listSize > 1) { // Handles edge case where list has only one element
			mergesort(leftList); // Recursive call on leftList
			mergesort(rightList); // Recursive call on rightList

			// Calls the mergeAndSortLists method which handles the sorting and merging of
			// bothList
			final int[] sortedList = sortAndMergeLists(leftList, rightList);

			// Overwrites the original list with the sorted list
			overWriteList(aList, sortedList, 0, finalIndex);
		}
	}

	/**
	 * Swaps two values in a list
	 */
	private static void swapValuesInList(final int[] list, final int index1, final int index2) {
		final int value1 = list[index1];
		final int value2 = list[index2];

		list[index1] = value2;
		list[index2] = value1;
	}

	/**
	 * Creates a new list from an existing list given a start index and end index to
	 * make the list from
	 */
	private static int[] makeListFromIndex(final int[] originalList, final int initialIndex, final int finalIndex) {
		final int[] finalList = new int[finalIndex - initialIndex + 1];

		int counter = 0;

		for (int i = initialIndex; i <= finalIndex; i++) {
			finalList[counter] = originalList[i];
			counter++;
		}

		return finalList;
	}

	/**
	 * Overwrites a list with another lists data starting from a given start index
	 * and end index for the list that will not be overwritten
	 */
	private static void overWriteList(final int[] listThatWillBeOverwritten, final int[] listThatWillDoOverWriting,
			final int initialIndex, final int finalIndex) {
		int listThatWillDoOverWritingIndex = 0;

		for (int i = initialIndex; i <= finalIndex; i++) {
			listThatWillBeOverwritten[i] = listThatWillDoOverWriting[listThatWillDoOverWritingIndex];
			listThatWillDoOverWritingIndex++;
		}
	}

	/**
	 * This method handles the operations for the mergesort method. Sorts two given
	 * lists then merges them and returns the merged list.
	 */
	private static int[] sortAndMergeLists(final int[] list1, final int[] list2) {
		int list1Index = 0;
		final int list1FinalIndex = list1.length - 1;
		int list2Index = 0;
		final int list2FinalIndex = list2.length - 1;
		int mergedListIndex = 0;

		final int[] mergedList = new int[list1FinalIndex + list2FinalIndex + 2];

		/**
		 * Compares both lists and adds values into mergedList in order until one of the
		 * lists runs out of elements
		 */
		while (list2Index <= list2FinalIndex && list1Index <= list1FinalIndex) {
			if (list2[list2Index] > list1[list1Index]) {
				mergedList[mergedListIndex] = list1[list1Index];
				list1Index++;

			} else if (list2[list2Index] < list1[list1Index]) {
				mergedList[mergedListIndex] = list2[list2Index];
				list2Index++;
			} else {
				mergedList[mergedListIndex] = list1[list1Index];
				mergedListIndex++;
				mergedList[mergedListIndex] = list2[list2Index];
				list1Index++;
				list2Index++;
			}
			mergedListIndex++;
		}

		/**
		 * Adds any leftover values to the mergedList after the other list has run out
		 * of values
		 */
		for (int i = list1Index; i <= list1FinalIndex; i++) {
			mergedList[mergedListIndex] = list1[i];
			mergedListIndex++;
		}
		for (int i = list2Index; i <= list2FinalIndex; i++) {
			mergedList[mergedListIndex] = list2[i];
			mergedListIndex++;
		}

		return mergedList;
	}
}