import { useState, forwardRef, useImperativeHandle } from "react";
import "./budgetType.css";

const BudgetType = forwardRef((props, ref) => {
    const [selectedType, setSelectedType] = useState("monthly");

    const handleTypeChange = (e) => {
        setSelectedType(e.target.value);
    };

    const handleLabelClick = (type) => {
        setSelectedType(type);
    };

    const setBudgetType = (type) => {
        setSelectedType(type);
    };

    useImperativeHandle(ref, () => ({
        getBudgetData: () => ({ selectedType }),
        setBudgetType
    }));

    return (
        <div className="BudgetType">
            <h1>Budget Type</h1>
            <div className="budget-options">
                <div
                    className={`option ${selectedType === "daily" ? "selected" : "unselected"}`}
                    onClick={() => handleLabelClick("daily")}
                >
                    <input
                        type="radio"
                        id="daily"
                        name="budgetType"
                        value="daily"
                        checked={selectedType === "daily"}
                        onChange={handleTypeChange}
                    />
                    <label htmlFor="daily">Daily</label>
                </div>
                <div
                    className={`option ${selectedType === "monthly" ? "selected" : "unselected"}`}
                    onClick={() => handleLabelClick("monthly")}
                >
                    <input
                        type="radio"
                        id="monthly"
                        name="budgetType"
                        value="monthly"
                        checked={selectedType === "monthly"}
                        onChange={handleTypeChange}
                    />
                    <label htmlFor="monthly">Monthly</label>
                </div>

                <div
                    className={`option ${selectedType === "yearly" ? "selected" : "unselected"}`}
                    onClick={() => handleLabelClick("yearly")}
                >
                    <input
                        type="radio"
                        id="yearly"
                        name="budgetType"
                        value="yearly"
                        checked={selectedType === "yearly"}
                        onChange={handleTypeChange}
                    />
                    <label htmlFor="yearly">Yearly</label>
                </div>
            </div>
        </div>
    );
});

export default BudgetType;