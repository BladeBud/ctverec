
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adam
 * @since 2025-01-13
 */

public class ArrayTreeDMSstatic {
    //---------------------------------------------------------------------------------------------
    private static int MAX_NODES = 5;
    private TreeNode[] nodes = new TreeNode[MAX_NODES];
    private int nextFreeIndex = 0;

    private class TreeNode {
        //---------------------------------------------------------------------------------------------
        int id;             // Node's unique identifier
        int parentId;       // Parent node's ID
        String name;        // Node name
        int nodeCount;      // Number of direct child nodes
        int docCount;       // Total documents in this node and its subtree
        int fieldPointer;   // Pointer to the next available field
        //  List<String> documents;  // Documents in this specific node

        TreeNode(int id, int parentId, String name) {
            this.id = id;
            this.parentId = parentId;
            this.name = name;
            this.nodeCount = 0;
            this.docCount = 0;
            this.fieldPointer = (id + 1);
            //   this.documents = new ArrayList<>();
        }
    }

    //INITIALIZE THE TREE FOR STATIC
    public void initArrayTree(){
        //---------------------------------------------------------------------------------------------
        for (int i = 0; i < MAX_NODES; i++) {
            nodes[i] = new TreeNode(i, i, null);
            nodes[i].fieldPointer = (i == MAX_NODES - 1) ? i : i + 1;  // Last index points to itself
        }
    }


    // Insert a new node into the array
    public int insertNode(int parentId, String nodeName) {
        //---------------------------------------------------------------------------------------------
        // Check if array is full
        if (nextFreeIndex >= MAX_NODES) {
//            throw new IllegalStateException("Tree array is full");
            MAX_NODES = MAX_NODES + 2; //change then according to match teh requirement
            initArrayTree();
        }

        // Validate parent node (if not root)
        if (parentId != nextFreeIndex && (parentId >= MAX_NODES || nodes[parentId] == null)) {
            throw new IllegalArgumentException("Invalid parent node");
        }

        int currentIndex = nextFreeIndex;
        // Create new node
        TreeNode newNode = new TreeNode(nextFreeIndex, parentId, nodeName);

        // Store node in array
        nodes[nextFreeIndex] = newNode;

        // Increment parent's child count if not root
        if (parentId != currentIndex) {
            nodes[parentId].nodeCount++;
        }

        // Update nextFreeIndex to the next available slot
        updateNextFreeIndexAdd();

        return nextFreeIndex -  1;
    }

    // Update to the next free index
    private void updateNextFreeIndexAdd() {
        //---------------------------------------------------------------------------------------------
        TreeNode node = nodes[nextFreeIndex];
        nextFreeIndex = node.fieldPointer;;

    }

    // Delete a node and its entire subtree
    public void deleteNode(int nodeIndex) {
        //---------------------------------------------------------------------------------------------
        // Validate node
        if (nodeIndex < 0 || nodeIndex >= MAX_NODES || nodes[nodeIndex] == null) {
            throw new IllegalArgumentException("Invalid node index");
        }

        for (int i = 0; i < MAX_NODES; i++) {
            if (nodes[i] != null && nodes[i].parentId == nodeIndex) {
                deleteNode(i);
            }
        }

        // Decrement parent's child count
        int parentId = nodes[nodeIndex].parentId;
        if (parentId != nodeIndex && nodes[parentId] != null) {
            nodes[parentId].nodeCount--;
        }

        UpdateNextFreeIndexDelete(nodeIndex);
    }

    public void UpdateNextFreeIndexDelete(int nodeIndex) {
        //---------------------------------------------------------------------------------------------
        nodes[nodeIndex].fieldPointer = nextFreeIndex;  // Make the deleted node point to the current free slot
        nextFreeIndex = nodeIndex;                     // Update the general pointer to deleted slot

        // Reset the deleted node's fields to their default values
        nodes[nodeIndex].name = null;
        nodes[nodeIndex].parentId = nodeIndex;
    }

    public static void main(String[] args) {
//--------------------------------------------------------------------------------------------------
        ArrayTreeDMSstatic dms = new ArrayTreeDMSstatic();
        dms.initArrayTree(); //static way of doing it

        System.out.println("pointer:" + dms.nextFreeIndex);

        // Create tree structure
        int rootIndex = dms.insertNode(0, "Root");
        int documentsIndex = dms.insertNode(rootIndex, "Documents");
        int projectsIndex = dms.insertNode(documentsIndex, "Projects");
        System.out.println("pointer:" + dms.nextFreeIndex);
       // dms.printIndexPointers();

        // Print initial tree
        System.out.println("Initial Tree Structure:");
        dms.printTree();
        System.out.println("pointer:" + dms.nextFreeIndex);
        //dms.printIndexPointers();

        // Delete a node
        System.out.println("\nAfter deleting Documents node:");
        dms.deleteNode(documentsIndex);
        dms.printTree();
        System.out.println("pointer:" + dms.nextFreeIndex);
       // dms.printIndexPointers();

        // Insert a new node , cehck delted position + expansion
        System.out.println("\nInserting a new node:");
        int newDocumentsIndex = dms.insertNode(rootIndex, "NewDocuments");
        int newDocument1Index = dms.insertNode(newDocumentsIndex, "Document1");
        int newDocumentsIndex2 = dms.insertNode(rootIndex, "NewDocuments");
        int newDocument1Index2 = dms.insertNode(newDocumentsIndex, "Document1");
        int newDocument1Index3 = dms.insertNode(newDocumentsIndex, "Document1");
        dms.printTree();
        System.out.println("pointer:" + dms.nextFreeIndex);
       // dms.printIndexPointers();


    }

    public void printTree() {
        for (int i = 0; i < MAX_NODES; i++) {
            if (nodes[i] != null) {
                TreeNode node = nodes[i];
                System.out.printf("Index: %d, ID: %d, Name: %s, Parent: %d, Children: %d, Point: %d\n", //Documents: %d
                        i, node.id, node.name, node.parentId, node.nodeCount, node.fieldPointer); //node.docCount

                // Print documents for this node
//                if (!node.documents.isEmpty()) {
//                    System.out.println(" Documents: " + node.documents);
//                }
            }
        }
    }
    public void printIndexPointers() {
        for (int i = 0; i < MAX_NODES; i++) {
            if (nodes[i] != null) {
                System.out.printf("Index: %d, Points to: %d\n", i, nodes[i].fieldPointer);
            }
        }
    }
}
