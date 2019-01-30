package vp.spring.rcs.web.dto;

import java.util.Comparator;

public class SortCommentById implements Comparator<CommentDTO>{
	public int compare(CommentDTO a, CommentDTO b) 
    { 
        return (int) (a.getId() - b.getId()); 
    } 
}
