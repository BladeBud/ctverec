import java.util.ArrayList;
import java.util.List;

public class ArrayTreeDMS {
    private static final int MAX_NODES = 1000;
    private TreeNode[] nodes = new TreeNode[MAX_NODES];
    private int nextFreeIndex = 0;

    // Nested TreeNode class
    private class TreeNode {
        int id;             // Node's unique identifier
        int parentId;       // Parent node's ID
        String name;        // Node name
        int nodeCount;      // Number of direct child nodes
        int docCount;       // Total documents in this node and its subtree
        int fieldPointer;   // Pointer to the next available field
        List<String> documents;  // Documents in this specific node

        TreeNode(int id, int parentId, String name) {
            this.id = id;
            this.parentId = parentId;
            this.name = name;
            this.nodeCount = 0;
            this.docCount = 0;
            this.fieldPointer = (id + 1);
            this.documents = new ArrayList<>();
        }
    }

    // Insert a new node into the array
    public int insertNode(int parentId, String nodeName) {
        // Check if array is full
        if (nextFreeIndex >= MAX_NODES) {
            throw new IllegalStateException("Tree array is full");
        }

        // Validate parent node (if not root)
        if (parentId != -1 && (parentId >= MAX_NODES || nodes[parentId] == null)) {
            throw new IllegalArgumentException("Invalid parent node");
        }

        // Create new node
        TreeNode newNode = new TreeNode(nextFreeIndex, parentId, nodeName);

        // Store node in array
        nodes[nextFreeIndex] = newNode;

        // Increment parent's child count if not root
        if (parentId != -1) {
            nodes[parentId].nodeCount++;
        }

        // Update nextFreeIndex to the next available slot
        updateNextFreeIndex();

        return nextFreeIndex - 1;
    }

    // Update to the next free index
    private void updateNextFreeIndex() {
        while (nextFreeIndex < MAX_NODES && nodes[nextFreeIndex] != null) {
            nextFreeIndex++;
        }
    }

    // Delete a node and its entire subtree
    public void deleteNode(int nodeIndex) {
        // Validate node
        if (nodeIndex < 0 || nodeIndex >= MAX_NODES || nodes[nodeIndex] == null) {
            throw new IllegalArgumentException("Invalid node index");
        }

        // Recursively delete all children first
        for (int i = 0; i < MAX_NODES; i++) {
            if (nodes[i] != null && nodes[i].parentId == nodeIndex) {
                deleteNode(i);
            }
        }

        // Decrement parent's child count
        int parentId = nodes[nodeIndex].parentId;
        if (parentId != -1 && nodes[parentId] != null) {
            nodes[parentId].nodeCount--;
        }

        // Clear the node
        nodes[nodeIndex] = null;

        // Update next free index if necessary
        if (nodeIndex < nextFreeIndex) {
            nextFreeIndex = nodeIndex;
        }
    }

    // Print the entire tree structure
    // Main method for demonstration
    public static void main(String[] args) {
        ArrayTreeDMS dms = new ArrayTreeDMS();

        System.out.println("Initial Tree Structure:");
        dms.printTree();

        // Create tree structure
        int rootIndex = dms.insertNode(-1, "Root");
        int documentsIndex = dms.insertNode(rootIndex, "Documents");
        int projectsIndex = dms.insertNode(rootIndex, "Projects");

        // Print initial tree
        System.out.println("Initial Tree Structure:");
        dms.printTree();

        // Delete a node
        System.out.println("\nAfter deleting Documents node:");
        dms.deleteNode(documentsIndex);
        dms.printTree();

        // Insert a new node to show reuse of deleted index
        System.out.println("\nInserting a new node:");
        int newDocumentsIndex = dms.insertNode(rootIndex, "NewDocuments");
        int newDocument1Index = dms.insertNode(newDocumentsIndex, "Document1");
        dms.printTree();
    }

    public void printTree() {
        for (int i = 0; i < MAX_NODES; i++) {
            if (nodes[i] != null) {
                TreeNode node = nodes[i];
                System.out.printf("Index: %d, ID: %d, Name: %s, Parent: %d, Children: %d, Documents: %d, Point: %d\n",
                        i, node.id, node.name, node.parentId, node.nodeCount, node.docCount, node.fieldPointer);

                // Print documents for this node
                if (!node.documents.isEmpty()) {
                    System.out.println("  Documents: " + node.documents);
                }
            }
        }
    }
}