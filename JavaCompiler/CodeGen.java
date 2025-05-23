class CodeGen {

    int interpreta(ArvoreSintatica arv) throws Exception {
        if (arv instanceof Num) {
            return ((Num) arv).num;
        }

        if (arv instanceof Soma) {
            return interpreta(((Soma) arv).arg1) + interpreta(((Soma) arv).arg2);
        }

        if (arv instanceof Sub) {
            return interpreta(((Sub) arv).arg1) - interpreta(((Sub) arv).arg2);
        }

        if (arv instanceof Mult) {
            return interpreta(((Mult) arv).arg1) * interpreta(((Mult) arv).arg2);
        }

        if (arv instanceof Div) {
            int divisor = interpreta(((Div) arv).arg2);
            if (divisor == 0) {
                throw new ArithmeticException("Divisão por zero");
            }
            return interpreta(((Div) arv).arg1) / divisor;
        }

        throw new Exception("Nó inválido na árvore sintática");
    }

    String geraCodigo(ArvoreSintatica arv) {
        return (geraCodigo2(arv) + "PRINT");
    }

    String geraCodigo2(ArvoreSintatica arv) {
        if (arv instanceof Div)
            return (geraCodigo2(((Div) arv).arg1) +
                    geraCodigo2(((Div) arv).arg2) +
                    "DIV\n");

        if (arv instanceof Sub)
            return (geraCodigo2(((Sub) arv).arg1) +
                    geraCodigo2(((Sub) arv).arg2) +
                    "SUB\n");

        if (arv instanceof Mult)
            return (geraCodigo2(((Mult) arv).arg1) +
                    geraCodigo2(((Mult) arv).arg2) +
                    "MULT\n");

        if (arv instanceof Soma)
            return (geraCodigo2(((Soma) arv).arg1) +
                    geraCodigo2(((Soma) arv).arg2) +
                    "SUM\n");

        if (arv instanceof Num)
            return ("PUSH " + ((Num) arv).num + "\n");

        return "";
    }
}
