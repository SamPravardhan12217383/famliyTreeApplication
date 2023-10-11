import java.util.*;

class FamilyMember {
    private String name;
    private String gender;
    private List<FamilyMember> children;

    public FamilyMember(String name, String gender) {
        this.name = name;
        this.gender = gender;
        this.children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public List<FamilyMember> getChildren() {
        return children;
    }

    public void addChild(FamilyMember child) {
        children.add(child);
        Collections.sort(children, (a, b) -> a.getName().compareTo(b.getName()));
    }
}

class FamilyTree {
    private FamilyMember root;

    public FamilyTree(String rootName, String rootGender) {
        this.root = new FamilyMember(rootName, rootGender);
    }

    public FamilyTree(){

    }

    public void addMember(String parentName, String childName, String childGender) {
        FamilyMember parent = findMember(root, parentName);
        if (parent != null) {
            FamilyMember child = new FamilyMember(childName, childGender);
            parent.addChild(child);
        } else {
            System.out.println("Parent not found in the family tree.");
        }
    }

    private FamilyMember findMember(FamilyMember member, String name) {
        if (member.getName().equals(name)) {
            return member;
        } else {
            for (FamilyMember child : member.getChildren()) {
                FamilyMember found = findMember(child, name);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    public void displayTree(FamilyMember member, int level) {
        if (member != null) {
            System.out.println(new String(new char[level]).replace('\0', ' ') + member.getName() + " (" + member.getGender() + ")");
            for (FamilyMember child : member.getChildren()) {
                displayTree(child, level + 1);
            }
        }
    }

    public void displayTree() {
        displayTree(root, 0);
    }
}

public class FamilyTreeApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        FamilyTree familyTree = new FamilyTree();
        System.out.println("//// Commands for input \\\\\\\\");
        System.out.println("'Member' for entering the member to thr family tree.");
        System.out.println("'Exit' for exiting and printing the family tree.g");

        FamilyTreeApp obj = new FamilyTreeApp();
        while(true){
            System.out.print("\n\nEnter the Command : ");
            String input1 = sc.nextLine();
            String check = obj.checkCond(input1);
            if (check.equals("true")){
                System.out.print("\n\nEnter name of NEW member : ");
                String menberName = sc.nextLine();
                System.out.print("Enter PARENT name of the member : ");
                String memberParent = sc.nextLine();
                System.out.print("Enter the Gender of the member : ");
                String memberGender = sc.nextLine();
                familyTree.addMember(memberParent, menberName, memberGender);
            }else if (check.equals("root")){
                System.out.print("\n\nEnter name of the ROOT Parent : ");
                String name = sc.nextLine();
                System.out.print("Enter the Gender of the ROOT Parent : ");
                String gender = sc.nextLine();
                familyTree = new FamilyTree(name, gender);
            }else{
                break;
            }
        }
        System.out.println("\nPrinting the family Tree ....\n\n");
        familyTree.displayTree();
    }

    private String checkCond(String input){

        switch (input){
            case "Member", "member", "MEMBER" -> {
                return "true";
            }
            case "Root", "ROOT", "root" -> {
                return "root";
            }
            case "Exit", "exit", "EXIT" -> {
                return "false";
            }
            default -> {
                System.out.println("Invalid choice entered!");
                return "true";
            }
        }
    }
}
