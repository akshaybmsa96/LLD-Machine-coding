package questions.fileSystem;

import java.util.List;

class Manager{

    Folder root;

//    boolean createFolder(String name, String path){
//
//    }
//
//    boolean createFile(String name, String path, String content){
//
//    }
//
//    boolean deleteChild(String path){
//
//    }
//
//    List<String> listItems(String path){
//
//    }

//    boolean moveFile(String path, String toPath){
//
//    }

    boolean moveFolder(String path, String toPath){

        if(path.equals(toPath)){
            return true;
        }

        DirectoryChild target = getDirectoryChild(path);
        DirectoryChild to = getDirectoryChild(toPath);
        if(!(target instanceof Folder)){
            throw  new RuntimeException("Invalid folder");
        }

        if(!isValidMovement(target, to)){
            throw  new RuntimeException("Invalid movement of folder");
        }
        else{

            if(to instanceof Folder){
                target.getParent().deleteChild(target.getName());
                target.changeParent((Folder) to);
                ((Folder) to).addChild(target);
                return true;
            } else {
                throw new RuntimeException("Not a Folder");
            }
        }
    }

    private boolean isValidMovement(DirectoryChild entityToMove, DirectoryChild toMoveTo ) {


        if(entityToMove == root){
            return false;
        }

        if(!(toMoveTo instanceof Folder)){
            return false;
        }

        if(toMoveTo == root && checkNameCollision((Folder) toMoveTo, entityToMove.getName())){
            return true;
        }

        return checkCycleDetection((Folder) toMoveTo, entityToMove) && checkNameCollision((Folder) toMoveTo, entityToMove.getName());
    }

    private boolean checkCycleDetection(Folder toMoveTo, DirectoryChild entityToMove){
        while(toMoveTo.getParent() != null && toMoveTo.getParent() != root){
            if(toMoveTo.getParent() == entityToMove){
                return false;
            }
            toMoveTo = toMoveTo.getParent();
        }

        return true;
    }

    private boolean checkNameCollision(Folder toMoveTo, String name){
        return !toMoveTo.children.containsKey(name);
    }

    boolean updateContent(String path, String content){

        File file = (File) getDirectoryChild(path);
        file.rewriteContent(content);

        return true;
    }

    boolean rename(String path, String name){

        DirectoryChild child = getDirectoryChild(path);

        Folder parent = child.getParent();
        if(parent == null || child == root){
            throw  new RuntimeException("Renaming not allowed");
        }
        if(!checkNameCollision(parent, name)){
            throw  new RuntimeException("Same not already exists");
        }
        parent.deleteChild(child.getName());
        child.rename(name);
        parent.addChild(child);

        return true;
    }

    DirectoryChild getDirectoryChild(String path){

        if(path.isBlank() || path.charAt(0)!='/'){
            throw new NullPointerException("path is not valid");
        }

        if(path.equals("/")){
            return root;
        }

        String[] paths = path.split("/");

        DirectoryChild currentDirectory = root;
        for(int i = 1; i < paths.length; i++){
            if(!(currentDirectory instanceof Folder)){
                throw new NullPointerException("path is not valid");
            }
            currentDirectory = currentDirectory.getChild(paths[i]);

            if(currentDirectory == null){
                throw new RuntimeException("path is not valid");
            }
        }

        return currentDirectory;
    }

}