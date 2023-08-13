package core.basesyntax.impl;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.ParserService;
import java.util.ArrayList;
import java.util.List;

public class ParserServiceImpl implements ParserService<FruitTransaction> {

    @Override
    public List<FruitTransaction> parse(List<String> inputData) {
        if (inputData.isEmpty()) {
            throw new RuntimeException("empty input parameters");
        }
        List<FruitTransaction> fruitTransactions = new ArrayList<>();
        for (int i = 1; i < inputData.size(); i++) {
            String[] data = inputData.get(i).split(",");
            FruitTransaction transaction = new FruitTransaction();
            String operationCode = data[0];
            FruitTransaction.Operation operation = findOperationByCode(operationCode);
            if (operation == null) {
                throw new IllegalArgumentException("Invalid operation code: " + operationCode);
            }
            transaction.setOperation(operation);
            transaction.setFruit(data[1]);
            transaction.setQuantity(Integer.parseInt(data[2]));
            fruitTransactions.add(transaction);
        }
        return fruitTransactions;
    }

    private FruitTransaction.Operation findOperationByCode(String code) {
        for (FruitTransaction.Operation operation : FruitTransaction.Operation.values()) {
            if (operation.getCode().equals(code)) {
                return operation;
            }
        }
        throw new RuntimeException("Can't find operation");
    }
}
