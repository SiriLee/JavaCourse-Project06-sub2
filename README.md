# 数值计算

## Question01
源代码：[Function.java](src/main/java/com/ecnu/Function.java)

## Question02
源代码：[DifferentiableFunction.java](src/main/java/com/ecnu/DifferentiableFunction.java)

## Question03
源代码：
- [Linear.java](src/main/java/com/ecnu/function/Linear.java)
- [Quadratic.java](src/main/java/com/ecnu/function/Quadratic.java)
- [Sin.java](src/main/java/com/ecnu/function/Sin.java)
- [NormalPDF.java](src/main/java/com/ecnu/function/NormalPDF.java)

说明：
- `Linear` 表示线性函数 $f(x)=kx+b$，同时实现了导数计算。
- `Quadratic` 表示二次函数 $f(x)=ax^2+bx+c$，并提供对应导数。
- `Sin` 表示 $f(x)=\sin(\omega x+\phi)$，导数为 $\omega\cos(\omega x+\phi)$。
- `NormalPDF` 表示标准形式的高斯函数核，并实现其导数。

测试说明：
- [FunctionTest.java](src/test/java/com/ecnu/function/FunctionTest.java) 主要验证 `function` 包中各类的 `eval` 和 `diff` 结果是否符合公式。
- 测试覆盖了线性函数、二次函数、正弦函数、正态分布函数，以及 `Function` 接口的多态调用场景。

## Question04
源代码：[NewtonRoot.java](src/main/java/com/ecnu/root/NewtonRoot.java)

测试说明：
- [NewtonRootTest.java](src/test/java/com/ecnu/root/NewtonRootTest.java) 主要验证 Newton 法求根的正确性。
- 测试覆盖了线性函数、二次函数、正弦函数的根求解结果，并验证了对没有实根的正态分布函数会抛出异常。

## Question05
源代码：[NewtonCotes.java](src/main/java/com/ecnu/integration/NewtonCotes.java)

测试说明：
- [NewtonCotesTest.java](src/test/java/com/ecnu/integration/NewtonCotesTest.java) 主要验证数值积分公式的正确性。
- 梯形公式使用线性函数进行测试，在 `n=10` 的复合划分下验证积分结果与解析积分一致。
- 辛普森公式使用 $\int_0^\pi \sin(x)\,dx$ 进行测试，在 `n=100` 的复合划分下数值结果逼近 2。

## Question06
### 1.准备数据
源代码：[IntegrationConvergenceTest.java](src/main/java/com/ecnu/driver/IntegrationConvergenceTest.java)

说明：
- 计算结果定向输出到 [sin_integral.csv](data/sin_integral.csv)