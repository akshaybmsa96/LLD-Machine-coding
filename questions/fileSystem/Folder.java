package questions.fileSystem;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class Folder implements DirectoryChild{
    private String name;
    private Folder parent;
    final Map<String, DirectoryChild> children = new ConcurrentHashMap<>();

    boolean addChild(DirectoryChild child){
        children.put(child.getName(), child);
        return true;
    }

    boolean addFolder(Folder newFolder){
        if(newFolder == null || newFolder.name == null){
            System.out.println("Folder name is null or empty");
            throw  new NullPointerException("Folder is null");
        }

        if(children.containsKey(newFolder.getName())){
            System.out.println("Folder already exists");
            throw new IllegalArgumentException("Folder already exists");
        } else {
            newFolder.changeParent(this);
            children.put(newFolder.getName(), newFolder);
            return true;
        }

    }

    public DirectoryChild getChild(String name){
        return children.getOrDefault(name, null);
    }

    void deleteChild(String childName){
        children.remove(childName);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Folder getParent() {
        return parent;
    }

    @Override
    public boolean rename(String name) {
       this.name = name;
       return true;
    }

    @Override
    public boolean changeParent(Folder folder) {
        this.parent = folder;
        return true;
    }
}
