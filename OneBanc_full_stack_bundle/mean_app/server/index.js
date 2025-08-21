import express from "express";
import cors from "cors";
import morgan from "morgan";
import mongoose from "mongoose";
import dotenv from "dotenv";
import path from "path";
dotenv.config();
const app = express();
app.use(cors());
app.use(express.json());
app.use(morgan("dev"));

// Simple models (Mongoose)
const CuisineSchema = new mongoose.Schema({ id:String, name:String, imageUrl:String }, { timestamps:true });
const DishSchema = new mongoose.Schema({ id:String, cuisineId:String, name:String, description:String, price:Number, imageUrl:String, veg:Boolean }, { timestamps:true });
const OrderSchema = new mongoose.Schema({ id:String, items:Array, subtotal:Number, tax:Number, total:Number, status:String }, { timestamps:true });

const Cuisine = mongoose.model("Cuisine", CuisineSchema);
const Dish = mongoose.model("Dish", DishSchema);
const Order = mongoose.model("Order", OrderSchema);

app.get("/api/health", (_req, res) => res.json({ ok:true, ts:Date.now() }));

app.get("/api/cuisines", async (_req, res) => {
  const data = await Cuisine.find().lean();
  res.json(data);
});

app.get("/api/dishes", async (req, res) => {
  const q = {};
  if (req.query.cuisineId) q.cuisineId = String(req.query.cuisineId);
  const data = await Dish.find(q).lean();
  res.json(data);
});

app.post("/api/cart/price", (req, res) => {
  const items = req.body.items || [];
  const subtotal = items.reduce((s, it) => s + (Number(it.price) * Number(it.qty)), 0);
  const tax = +(subtotal * 0.18).toFixed(2);
  const total = +(subtotal + tax).toFixed(2);
  res.json({ subtotal, tax, total });
});

import crypto from "crypto";
app.post("/api/orders", async (req, res) => {
  const id = crypto.randomUUID();
  const items = req.body.items || [];
  const subtotal = items.reduce((s, it) => s + (Number(it.price) * Number(it.qty)), 0);
  const tax = +(subtotal * 0.18).toFixed(2);
  const total = +(subtotal + tax).toFixed(2);
  const order = await Order.create({ id, items, subtotal, tax, total, status: "PLACED" });
  res.status(201).json(order);
});

const port = process.env.PORT || 8080;
mongoose.set('strictQuery', true);
mongoose.connect(process.env.MONGO_URI || "mongodb://localhost:27017/onebanc_restaurant").then(() => {
  app.listen(port, () => console.log(`🚀 MERN API listening on http://localhost:${port}`));
}).catch(err => console.error("Mongo connection failed:", err));
