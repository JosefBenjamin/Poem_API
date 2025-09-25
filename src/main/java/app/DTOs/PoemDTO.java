package app.DTOs;

import app.entities.Poem;

public record PoemDTO() {
    static Integer id;
    static String title;
    static String poem;
    static String style;

    public PoemDTO convertToDTO(Poem poem){
        PoemDTO result = new PoemDTO();
        result.id = poem.getId();
        result.title = poem.getTitle();
        result.poem = poem.getPoem();
        result.style = poem.getStyle();
        return result;
    }

    public Poem convertToEntity(){
        Poem result = new Poem();
        result.setId(this.id);
        result.setTitle(this.title);
        result.setPoem(this.poem);
        result.setStyle(this.style);
        return result;
    }
    public Integer getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getPoem(){
        return poem;
    }

    public String getStyle(){
        return style;
    }
}
