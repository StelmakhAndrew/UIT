package project.com.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.com.Entity.Comment;
import project.com.Repository.CommentRepository;
import project.com.Service.CommentService;

import java.util.List;

/**
 *
 */
@Service
public class CommentServiceImpl implements CommentService {


    /**
     *
     */
    @Autowired
    private CommentRepository commentRepository;


    /**
     * @param comment
     */
    @Override
    public void createComment(Comment comment) {
        commentRepository.save(comment);
    }

    /**
     * @return
     */
    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    /**
     * @return
     */
    @Override
    public List<Comment> findAllSortByDate() {
            return commentRepository.findAllByOrderByDateDesc();
    }

    /**
     * @param book_id
     * @return
     */
    @Override
    public List<Comment> findComentsForThisBookSortByDate(Long book_id) {
        return commentRepository.findAllByBook_IdOrderByDateDesc(book_id);
    }


}
