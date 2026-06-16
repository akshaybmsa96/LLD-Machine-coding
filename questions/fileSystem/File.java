package questions.fileSystem;

class File implements DirectoryChild{
    FileType type;
    String content;
    private String name;
    private Folder parent;

    boolean rewriteContent(String newContent){
        this.content = newContent;
        return true;
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
    public DirectoryChild getChild(String name) {
        return null;
    }

    public boolean rename(String name) {
        return false;
    }

    @Override
    public boolean changeParent(Folder folder) {
        parent = folder;
        return false;
    }
}
