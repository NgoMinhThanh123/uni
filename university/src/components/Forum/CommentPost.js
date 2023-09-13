import React, { useState } from 'react';
import { Form, Button } from 'react-bootstrap';

const CommentPost = ({ questionId, onCommentSubmit }) => {
  const [comment, setComment] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    if (comment.trim() !== '') {
      onCommentSubmit(questionId, comment);
      setComment('');
    }
  };

  return (
    <Form onSubmit={handleSubmit} className="mt-2">
      <Form.Group controlId={`comment-${questionId}`}>
        <Form.Control
          type="text"
          placeholder="Add a comment..."
          value={comment}
          onChange={(e) => setComment(e.target.value)}
        />
      </Form.Group>
      <Button variant="outline-primary" type="submit">
        Comment
      </Button>
    </Form>
  );
};

export default CommentPost;