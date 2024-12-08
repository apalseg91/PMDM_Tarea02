package dam.pmdm.tareamariobros;

public class CharacterData {
    private String name;
    private String skill;
    private String description;
    private int image;


    public CharacterData(String name, String skill, String description, int image) {
        this.name = name;
        this.skill = skill;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
