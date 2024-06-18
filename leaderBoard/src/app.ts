import express, { Application } from 'express';
import libraryRoutes from './routes/libraryRoutes';

const app: Application = express();

app.use(express.json());
app.use('/api', libraryRoutes);

export default app;
