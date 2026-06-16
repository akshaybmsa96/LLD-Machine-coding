package questions.fileSystem;

interface DirectoryChild {

    String getName();

    Folder getParent();

    DirectoryChild getChild(String name);


    boolean rename(String name);

    boolean changeParent(Folder folder);
}