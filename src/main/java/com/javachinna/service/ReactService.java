package com.javachinna.service;


import com.javachinna.model.CommentUniversity;
import com.javachinna.model.Emoji;
import com.javachinna.model.React;
import com.javachinna.repo.CommentUniversityRepository;
import com.javachinna.repo.ReactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReactService implements IReactService {



    @Autowired
    ReactRepository reactRepository;
    @Autowired
    CommentUniversityRepository commentrepo;

     public React save(Long id, React react){
         CommentUniversity commentUniversity = commentrepo.findById(id).orElse(null);
          react.setCommentUniversity(commentUniversity);
         assert commentUniversity != null;
         react.setUser(commentUniversity.getUser());
         react.setPartnerInstitution(commentUniversity.getPartnerInstitution());

     return  reactRepository.save(react);
}
    public List<React> findAllByCommentId(Long id){
    return  reactRepository.findAllByCommentId(id);
    }

    public List<React> findAllByCommentIdAndEmoji(Long idComment, Emoji em){
        return  reactRepository.findAllByCommentIdAndEmoji(idComment, em);
    }

    @Override
    public Long countAllByCommentId(Long idComment) {
        return reactRepository.countAllByCommentId(idComment);
    }
}
