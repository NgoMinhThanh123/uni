import { useContext, useState } from "react";
import { MyUserContext } from "../../App";
import Apis, { authApi, endpoints } from "../../configs/Apis";
import MySpinner from "../../layout/MySpinner";
import { useEffect } from "react";
import { Link } from "react-router-dom";
import './Post.css'
import { Button, Col, Container, Form, Pagination, Row } from "react-bootstrap";
import formatDate from "../FormatDare/FormatDate";
import moment from 'moment';

const Post = () => {
    const [user] = useContext(MyUserContext);
    const [post, setPost] = useState([]);
    const [showInput, setShowInput] = useState(false);
    const [title, setTitle] = useState();
    const [content, setContent] = useState();
    const [currentPage, setCurrentPage] = useState(1);
    const postsPerPage = 6;
    const indexOfLastPost = currentPage * postsPerPage;
    const indexOfFirstPost = indexOfLastPost - postsPerPage;
    const [searchKeyword, setSearchKeyword] = useState("");


    useEffect(() => {
        const loadPost = async () => {
            let { data } = await Apis.get(endpoints['posts'], {
                params: { kw: searchKeyword },
            });
            setPost(data);
        }
    
        loadPost();
    }, [searchKeyword]);

    const handleSearch = async () => {
        console.log("searchKeyword:", searchKeyword); // In ra giá trị searchKeyword để kiểm tra
        try {
            const { data } = await Apis.get(endpoints['posts'], {
                params: { kw: searchKeyword },
            });
            setPost(data);
        } catch (error) {
            console.error("Error searching posts:", error);
        }
    };

    const handleToggleInput = () => {
        setShowInput(!showInput);
    };

    const handlePostSubmit = () => {
        const process = async () => {
            let { data } = await authApi().post(endpoints['add-post'], {
                "title": title,
                "content": content,
            });

            setPost([...post, data]);

            setTitle("");
            setContent("");
            setShowInput(false);
        }

        process();
    };
    const currentPosts = post
    .filter((p) =>
        p.title.toLowerCase().includes(searchKeyword.toLowerCase()) ||
        p.content.toLowerCase().includes(searchKeyword.toLowerCase())
    )
    .slice(indexOfFirstPost, indexOfLastPost);
    const pageNumbers = [];
    for (let i = 1; i <= Math.ceil(post.length / postsPerPage); i++) {
        pageNumbers.push(i);
    }

    if (post === null)
        return <MySpinner />

    return <>
        <div>
            <h3 className="forum">Diễn đàn thảo luận môn học</h3>
            <Container className="mt-4 mb-4">
                <Row>
                    <Col sm={4}>
                        <Form className="d-flex">
                            <Form.Control
                                type="search"
                                placeholder="Search"
                                className="me-2"
                                aria-label="Search"
                                value={searchKeyword}
                                onChange={(e) => setSearchKeyword(e.target.value)}
                            />
                            <Button className="btn-search" onClick={handleSearch}>
                                Tìm kiếm
                            </Button>
                        </Form>
                    </Col>
                </Row>
            </Container>
            {!showInput && (
                <Button className="forum-btn" onClick={handleToggleInput}>Đăng bài</Button>
            )}
            {showInput && (
                <div className="post-container">
                    <div>
                        <p className="title">Tiêu đề</p>
                        <textarea className="inputTitle" placeholder="Nhập tiêu đề bài viết"
                            value={title} onChange={e => setTitle(e.target.value)}></textarea>
                    </div>
                    <div>
                        <p className="title">Nội dung</p>
                        <textarea className="inputContent" placeholder="Nhập nội dung bài viết"
                            value={content} onChange={e => setContent(e.target.value)}></textarea>
                    </div>
                    <Button className="btn-title btnSubmitTile" onClick={handlePostSubmit}>Đăng</Button>
                    <Button className="btn-title btnCancelTile" onClick={handleToggleInput}>Hủy</Button>
                </div>
            )}
            {post.length > 0 && (
                <table className="table">
                    <thead>
                        <tr>
                            <th>Tên bài post</th>
                            <th>Người đăng</th>
                            <th>Thời gian đăng</th>
                        </tr>
                    </thead>
                    <tbody>
                        {currentPosts.map(p => (
                            <tr key={p.id}>
                                <td>
                                    <Link to={`/posts/${p.id}`} className="post-link">
                                        {p.title}
                                    </Link>
                                </td>
                                <td>{p.userId.username}</td>
                                <td>{formatDate(p.postTime)}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
        <Pagination>
            {pageNumbers.map(number => (
                <Pagination.Item key={number} active={currentPage === number} onClick={() => setCurrentPage(number)}>
                    {number}
                </Pagination.Item>
            ))}
        </Pagination>
    </>

}


export default Post