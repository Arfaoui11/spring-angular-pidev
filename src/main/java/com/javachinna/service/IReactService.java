package com.javachinna.service;

import com.javachinna.model.Emoji;
import com.javachinna.model.React;

import java.util.List;

public interface IReactService {
    public React save(Long id, React react);
    List<React> findAllByCommentId(Long id);
    List<React> findAllByCommentIdAndEmoji(Long idComment, Emoji em);
    Long  countAllByCommentId(Long idComment) ;
}
