import { Container } from "react-bootstrap";
import Footer from "./Footer/Footer";
import Header from "./Header/Header";

function BaseLayout({ children }) {
    return (<>
        <Header />
        <Container>
          <div className="">{children}</div>
        </Container>
        <Footer />
    </>)
}

  export default BaseLayout;
