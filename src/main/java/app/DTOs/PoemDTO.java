package app.DTOs;

import app.entities.Poem;

public record PoemDTO() {
    static Integer id;
    static String title;
    static String poem;
    static String style;

    public static PoemDTO convertToDTO(Poem poem){
        PoemDTO result = new PoemDTO();
        result.id = poem.getId();
        result.title = poem.getTitle();
        result.poem = poem.getPoem();
        result.style = poem.getStyle();
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
