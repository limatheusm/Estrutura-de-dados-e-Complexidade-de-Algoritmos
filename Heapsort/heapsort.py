class Heapsort:
  def sort(self, arr):
    self.__build_max_heap(arr)
    size = len(arr) - 1

    for i in range(size, -1, -1):
      self.__max_heapify(arr, 0, i)
      # swap last index with first index already sorted
      arr[0], arr[i] = arr[i], arr[0]

  def __build_max_heap(self, arr):
    size = len(arr) - 1
    heap_size = int(size / 2)

    for i in range(heap_size, -1, -1):
      self.__max_heapify(arr, i, size)

  def __max_heapify(self, arr, root_index, size):
    '''
    Heapify subtree rooted at index root_index

    Parameters
    ----------
    arr: list
    List of values

    root_index: int
    Root index

    size: int
    Index of the last valid element of the list
    ----------
    '''
    # Initialize largest as root 
    largest = root_index
    
    # Set index of left and right children
    left = (2 * root_index) + 1
    right = left + 1

    # See if left child of root exists and is greater than root
    if(left <= size and arr[left] > arr[largest]):
        largest = left

    # See if right child of root exists and is greater than root
    if(right <= size and arr[right] > arr[largest]):
        largest = right

    # Change root, if needed 
    if largest != root_index: 
        # swap 
        arr[root_index], arr[largest] = arr[largest], arr[root_index]
        # Heapify the new root. 
        self.__max_heapify(arr, largest, size) 

if __name__ == "__main__":
    arr = [4, 1, 3, 2, 16, 9, 10, 14, 8, 7]
    Heapsort().sort(arr)
    print(arr)