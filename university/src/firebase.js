import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";
import { getFirestore } from "firebase/firestore";
import { getStorage} from "firebase/storage";

const firebaseConfig = {
  apiKey: "AIzaSyBEY8pxkeSPa5kO6V9BbY8cUIWrihfNHKw",
  authDomain: "chat-8f3ed.firebaseapp.com",
  projectId: "chat-8f3ed",
  storageBucket: "chat-8f3ed.appspot.com",
  messagingSenderId: "396688342365",
  appId: "1:396688342365:web:d2be897352d9e75937fcb4"
};

export const app = initializeApp(firebaseConfig);
export const auth = getAuth();
export const storage = getStorage();
export const db = getFirestore();