import { Router } from "express";
import { fetchDepartments, fetchBooks } from "../controller/libraryController";

const router: Router = Router();

router.get('/leader/departments', fetchDepartments);
router.get('/leader/books', fetchBooks);

export default router;