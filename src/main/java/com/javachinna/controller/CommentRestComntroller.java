package com.javachinna.controller;

import com.javachinna.model.Comment;
import com.javachinna.service.ICommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController

@RequestMapping("/Comment")

public class CommentRestComntroller {

    @Autowired
    ICommentService iCommentService;

    @PostMapping("/addComment/{idTopic}/{idUser}")
    public void AddAffectCommentList(@RequestBody Comment comment, @PathVariable("idTopic") Long idTopic, @PathVariable("idUser") Long idUser)
    {
        iCommentService.AddAffectCommentList(comment,idTopic,idUser);

    }

    @ApiOperation(value = "Update Comment")
    @PutMapping("/updateComment/{idTopic}/{idUser}")
    @ResponseBody

    public Comment updateComment(@RequestBody Comment comment, @PathVariable("idTopic") Long idTopic, @PathVariable("idUser") Long idUser)
    {
        return iCommentService.upDateComment(comment,idTopic,idUser);

    }

    @ApiOperation(value = "See All comment list")
    @GetMapping("/getAllComment")
    @ResponseBody
    public List<Comment> getAllComments()
    { return iCommentService.getAllComments(); }


    @ApiOperation(value = "Delete Comment")
    @DeleteMapping("/deleteComment")
    @ResponseBody

    public void deleteComment(Integer idComment)
    {
        iCommentService.deleteComment(idComment);
    }

    @ApiOperation(value = "List of Comments By Topic ")
    @GetMapping("/getAllCommentsByTopic/{idTopic}")
    @ResponseBody
    public List<Comment> getAllCommentsByTopic(@PathVariable(name = "idTopic") Long idTopic)
    {
        return  iCommentService.getAllCommentsByTopic(idTopic);
    }


        @PostMapping("/addLikeComment/{idComment}")
    @ApiOperation(value = " Add LikeComment ")
    void likeComment(@PathVariable(name = "idComment") Integer idComment){
        iCommentService.likeComment(idComment);
    }


    @PostMapping("/addDisLikeComment/{idComment}")
    @ApiOperation(value = " Add DisLike Comment ")
    void dislikeComment(@PathVariable(name = "idComment") Integer idComment)
    {
        iCommentService.dislikeComment(idComment);
    }
///////////////REACT////////////////////
    /*
    @PostMapping("/{idReact}")
    public ReactComment save(@RequestBody ReactComment reactComment , @PathVariable("idReact") Long idReact){
        return  iCommentService.save(idReact,reactComment) ;
    }
    @GetMapping("/{idReact}")
    public List<ReactComment> findAllByIdComment(@PathVariable("idReact") Long idReact){
        return  iCommentService.findAllByIdComment(idReact);
    }
    @GetMapping("/emoji/{idReact}")
    public List<ReactComment> findAllByCommentIdAndEmoji(@PathVariable("idReact") Long idReact, @RequestBody TypeRating typeRating){
        return  iCommentService.findAllByIdCommentAndEmoji(idReact,typeRating);
    }

     */
/////////////////////////////////////////////

}