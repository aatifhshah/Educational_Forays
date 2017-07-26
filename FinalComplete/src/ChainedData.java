
public class ChainedData {

	private Node firstNode;
	private Node currentNode;
	private int Size;
	
	public ChainedData() {

	}
	
	public int HashFunc(String s) {
		return ((s.length())%10);
	}

	public String getFile(String name) {
		String number = null;
		Node currentNode = firstNode;
		boolean found = false;
		while(!found && (currentNode != null)){
			if(name.equals(currentNode.name)){
				found = true;
				number = currentNode.location;
			}
			else
				currentNode = currentNode.next;
		}
		return number;
	}

	private class Node {
		private String name;
		private String location;
		private Node next;
		
		private Node(String Name, String fileLocation) {
			this(Name, fileLocation, null);
		}
		private Node(String Name, String fileLocation, Node nextNode){
			name = Name;
			location = fileLocation;
			next = nextNode;
		}
	}

	public boolean contains(String newEntry) {
		boolean found = false;
		Node currentNode = firstNode;
		
		while(!found && (currentNode != null)){
			if(newEntry.equals(currentNode.name)){
				found = true;
			}
			else
				currentNode = currentNode.next;
		}
		return found;
	}

	public boolean add(String newName, String pdflocation) {
		Node newNode = new Node(newName, pdflocation);
		newNode.next = firstNode;
		firstNode = newNode;
		Size++;
		
		return true;
	}
}
