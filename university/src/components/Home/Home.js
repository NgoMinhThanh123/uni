
import { Link } from "react-router-dom";
import "./Home.css"
import { Carousel } from "react-bootstrap";

const Home = () => {


    return (
        <>
            <h2 className="welcome">Chào mừng đến với Đại Học Mở TP.HCM</h2>

            <div>
                <Carousel interval={3000} data-bs-theme="dark">
                    <Carousel.Item>
                        <img
                            className="d-block w-100"
                            src="https://ou.edu.vn/wp-content/uploads/2021/03/CTLK.png"
                            alt="First slide"
                            style={{ height: "400px" }}
                        />
                    </Carousel.Item>
                    <Carousel.Item>
                        <img
                            className="d-block w-100"
                            src="https://ou.edu.vn/wp-content/uploads/2023/07/baac7a93ffb32ced75a2.jpg"
                            alt="Second slide"
                            style={{ height: "400px" }}
                        />
                    </Carousel.Item>
                    <Carousel.Item>
                        <img
                            className="d-block w-100"
                            src="https://ou.edu.vn/wp-content/uploads/2023/02/Banner-Thac-si-TESOL_1222x465.jpg"
                            alt="Third slide"
                            style={{ height: "400px" }}
                        />
                    </Carousel.Item>
                </Carousel>
            </div>

            <div className="introduce">
                <div>
                    <h3>GIỚI THIỆU CHUNG</h3>
                    Được thành lập vào năm 1990 và trở thành trường đại học công lập từ năm 2006, đến nay Trường Đại học Mở Thành phố Hồ Chí Minh là trường đại học đa ngành trực thuộc Bộ Giáo dục và Đào tạo, có nhiệm vụ đào tạo đại học và sau đại học, với các hình thức đào tạo chính quy và giáo dục thường xuyên, đào tạo các điểm vệ tinh,…nhằm đáp ứng nhu cầu học tập đa dạng của xã hội, góp phần tăng cường đội ngũ cán bộ khoa học-kỹ thuật cho đất nước.
                </div>
            </div>
            <hr className="seperate-black" />

            <div className="info">
                <div>
                    <h5>Tên trường</h5>
                    <div>Tiếng Việt: Trường Đại học Mở Thành Phố Hồ Chí Minh</div>
                    <div>Tiếng Anh: Ho Chi Minh City Open University</div>
                    <h5>Tên viết tắt của trường</h5>
                    <div>Tiếng Việt: Trường Đại học Mở Tp. Hồ Chí Minh</div>
                    <div>Tiếng Anh: HCMCOU</div>
                    <h5>Tên trước đây</h5>
                    <div>Viện Đào Tạo Mở Rộng TP. Hồ Chí Minh (từ năm 1990)Đại học Mở Bán Công TP. Hồ Chí Minh (từ năm 1993 đến năm 2005)</div>
                </div>
                <div>
                    <h5>Cơ quan/Bộ chủ quản</h5>
                    <div>Trường Đại học Mở TP. Hồ Chí Minh trực thuộc Bộ giáo dục và Đào tạo</div>
                    <h5>Địa chỉ liên lạc</h5>
                    <div>Số 97 Võ Văn Tần, Phường Võ Thị Sáu, Quận 3, TP. Hồ Chí Minh</div>
                    <div>Điện thoại: (028) 3930 0210</div>
                    <div>Fax: (028) 3930 0083</div>
                    <div>E-mail: ou@ou.edu.vn</div>
                    <div>Website: www.ou.edu.vn</div>
                </div>
            </div>

            <div className="view">
                <img loading="lazy" width="1300" height="2000" 
                src="https://ou.edu.vn/wp-content/uploads/2022/09/Sumang-TNOU2022.png" alt="" className="wp-image-33616" 
                srcSet="https://ou.edu.vn/wp-content/uploads/2022/09/Sumang-TNOU2022.png 2109w, https://ou.edu.vn/wp-content/uploads/2022/09/Sumang-TNOU2022-175x300.png 175w, https://ou.edu.vn/wp-content/uploads/2022/09/Sumang-TNOU2022-598x1024.png 598w, https://ou.edu.vn/wp-content/uploads/2022/09/Sumang-TNOU2022-768x1315.png 768w, https://ou.edu.vn/wp-content/uploads/2022/09/Sumang-TNOU2022-897x1536.png 897w, https://ou.edu.vn/wp-content/uploads/2022/09/Sumang-TNOU2022-1196x2048.png 1196w" sizes="(max-width: 2109px) 100vw, 2109px"/>
            </div>
            <div className="contain">
                <div className="logo">
                    <div>
                        <img className="alignnone size-full wp-image-47" src="https://ou.edu.vn/wp-content/uploads/2016/08/Logo.png" alt="logo2" />
                    </div>
                    <div>
                        <div>
                            Sứ mạng: Trường Đại học Mở thành phố Hồ Chí Minh thực hiện giáo dục mở, tạo bình đẳng cho mọi người trong giáo dục đại học, góp phần xây dựng xã hội học tập, nâng cao tri thức và năng lực nghề nghiệp cho người học bằng các phương thức linh hoạt, thuận tiện và hiệu quả.
                        </div>
                        <div className="mission">
                            Tầm nhìn: Trường Đại học Mở thành phố Hồ Chí Minh phấn đấu trở thành Đại học thực hiện giáo dục mở, định hướng ứng dụng với chất lượng cao.
                        </div>
                    </div>
                </div>
                <hr className="seperate" />

                <div className="footer-home">
                    <div>
                        <div>THÔNG TIN CHUNG</div>
                        <ul>
                            <li>Địa chỉ: 35 – 37 Hồ Hảo Hớn, Phường Cô Giang, Quận 1, TP. HCM.</li>
                            <li>Điện thoại: 028-38364748.</li>
                            <li>Fax: 028-39207639 hoặc 028-39207640.</li>
                            <li>E-mail: ou@ou.edu.vn.</li>
                        </ul>
                    </div>
                    <div>
                        <div>CÁC CƠ SỞ TRỰC THUỘC</div>
                        <ul>
                            <li>Địa điểm 1:97 Võ Văn Tần, P. Võ Thị Sáu, Q. 3, TP. Hồ Chí Minh.</li>
                            <li>Địa điểm 2:35-37 Hồ Hảo Hớn, P. Cô Giang, Q. 1 , TP. Hồ Chí Minh</li>
                            <li>Địa điểm 3:371 Nguyễn Kiệm, P. 3, Q. Gò Vấp, TP. Hồ Chí Minh.</li>
                            <li>Địa điểm 4:02 Mai Thị Lựu, P. Đa Kao, Q. 1, TP. Hồ Chí Minh.</li>
                            <li>Địa điểm 5:68 Lê Thị Trung, P. Phú Lợi, TP. Thủ Dầu Một, Tỉnh Bình Dương.</li>
                            <li> Địa điểm 6:Đường số 9, P. Long Bình Tân, TP. Biên Hòa, Tỉnh Đồng Nai.</li>
                            <li>Địa điểm 7:Số 04 đường Tân Định, P. Ninh Hiệp, Thị Xã Ninh Hòa,Tỉnh Khánh Hòa.</li>
                        </ul>
                    </div>
                </div>
            </div>

        </>
    )
}

export default Home;