package com.example.getmesocialservice.resource;

import com.example.getmesocialservice.exception.InvalidIdTokenException;
import com.example.getmesocialservice.model.Comment;
import com.example.getmesocialservice.model.FirebaseUser;
import com.example.getmesocialservice.model.Photo;
import com.example.getmesocialservice.service.CommentService;
import com.example.getmesocialservice.service.FirebaseService;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentResource {
    @Autowired
    private CommentService commentService;

    @Autowired
    private FirebaseService firebaseService;

    @PostMapping
    public Comment saveComment(@RequestBody @Valid Comment comment,
                               @RequestHeader (name="idToken") String idToken)
            throws IOException, FirebaseAuthException, InvalidIdTokenException {
        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);
        comment.setCreatedBy(firebaseUser.getEmail());

        if (firebaseUser != null){
            return commentService.saveComment(comment);
        }else {
            throw new InvalidIdTokenException();
        }
    }

    @GetMapping
    public List<Comment> getAllComments(){
        return commentService.getAllComments();
    }

    @GetMapping("/find-by-id")
    public Comment getById(@RequestParam(name = "commentId") String commentId){
        return commentService.getById(commentId);
    }

    @PutMapping
    public Comment updateComment(@RequestBody @Valid Comment comment,
                                 @RequestParam(name = "commentId") String commentId,
                                 @RequestHeader (name="idToken") String idToken)
            throws IOException, FirebaseAuthException, InvalidIdTokenException {
        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);
        Comment specificComment = commentService.getById(commentId);

        if (firebaseUser != null && firebaseUser.getEmail().equals(specificComment.getCreatedBy())){
            return commentService.updateComment(comment);
        }else {
            throw new InvalidIdTokenException();
        }
    }

    @DeleteMapping
    public void deleteComment(@RequestParam(name = "commentId") String commentId,
                              @RequestHeader (name="idToken") String idToken)
            throws IOException, FirebaseAuthException, InvalidIdTokenException {
        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);
        Comment specificComment = commentService.getById(commentId);

        if (firebaseUser != null && firebaseUser.getEmail().equals(specificComment.getCreatedBy())){
            commentService.deleteComment(commentId);
        }else {
            throw new InvalidIdTokenException();
        }
    }

}
