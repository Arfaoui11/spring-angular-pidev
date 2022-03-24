package com.javachinna.controller;

import com.javachinna.model.Comment;
import com.javachinna.model.RatingPartner;
import com.javachinna.model.ReactComment;
import com.javachinna.model.TypeRating;
import com.javachinna.service.ICommentService;
import com.javachinna.service.IReactCommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController

@RequestMapping("/Comment")

public class CommentRestComntroller {

    @Autowired
    ICommentService iCommentService;
    @Autowired
    IReactCommentService iReactCommentService;

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


//////////////////////////////////_________REACT_________///////////////////////////////////////


@PostMapping("/addReact")
@ResponseBody
@ApiOperation(value = "addReact ")
public void addReact(@RequestBody ReactComment reactComment){
    iReactCommentService.addReact(reactComment);
}

    @GetMapping("/getReactByCommentAndUser/{idComment}/{idUser}")
    @ResponseBody
    @ApiOperation(value = "get Reaction By Comment and User ")
    public List<ReactComment> getReactByCommentAndUser(@PathVariable("idComment") Integer idComment,
                                                            @PathVariable("idUser") Long idUser){
        return iReactCommentService.getReactByCommentAndUser(idComment,idUser);
    }
    @PostMapping("/addReactAndAffectToUserAndComment/{idUser}/{idComment}")
    @ResponseBody
    @ApiOperation(value = "addDemandAndAssignToUserAndComment ")
    public void addReactAndAffectToUserAndComment(TypeRating typeRating, @PathVariable("idUser") Long idUser
            , @PathVariable("idComment") Integer idComment){
        iReactCommentService.addReactAndAffectToUserAndComment(typeRating,idUser,idComment);
    }


/////////////////////////////////////////////

}