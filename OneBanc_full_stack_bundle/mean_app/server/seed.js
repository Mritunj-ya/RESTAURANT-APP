import mongoose from "mongoose";
import dotenv from "dotenv";
dotenv.config();
mongoose.set('strictQuery', true);
await mongoose.connect(process.env.MONGO_URI || "mongodb://localhost:27017/onebanc_restaurant");
const Cuisine = mongoose.model("Cuisine", new mongoose.Schema({ id:String, name:String, imageUrl:String }));
const Dish = mongoose.model("Dish", new mongoose.Schema({ id:String, cuisineId:String, name:String, description:String, price:Number, imageUrl:String, veg:Boolean }));

await Cuisine.deleteMany({});
await Dish.deleteMany({});
await Cuisine.insertMany([
  { id: "indian", name: "Indian" },
  { id: "italian", name: "Italian" },
  { id: "chinese", name: "Chinese" }
]);
await Dish.insertMany([
  { id: "dosa", cuisineId: "indian", name: "Masala Dosa", price: 120, veg: true },
  { id: "butter-chicken", cuisineId: "indian", name: "Butter Chicken", price: 260, veg: false },
  { id: "pizza", cuisineId: "italian", name: "Margherita Pizza", price: 350, veg: true }
]);
console.log("Seeded sample data");
process.exit(0);
