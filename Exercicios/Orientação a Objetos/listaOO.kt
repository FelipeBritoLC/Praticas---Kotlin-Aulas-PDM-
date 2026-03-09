import java.lang.IllegalArgumentException

// --- QUESTÃO 1 ---
class Livro(val titulo: String, val autor: String, val anoPublicacao: Int) {
    
    init {
        // Validação no construtor primário
        if (anoPublicacao > 2026 || anoPublicacao < 0) {
            throw IllegalArgumentException("Ano de publicação inválido: $anoPublicacao")
        }
    }

    constructor(titulo: String, autor: String) : this(titulo, autor, 2026) {
        println("Aviso: Livro criado com ano padrão (2026).")
    }

    fun exibirFicha() {
        println("Ficha Bibliográfica: '$titulo' | Autor: $autor | Ano: $anoPublicacao")
    }
}

// --- QUESTÃO 2 ---
class ContaBancaria(val numero: Int, val titular: String, var saldo: Double = 0.0) {

    init {
        totalContas++
    }

    companion object {
        private var totalContas: Int = 0
        
        fun exibirTotalContas() {
            println("Total de contas criadas: $totalContas")
        }
    }

    fun depositar(valor: Double) {
        if (valor <= 0) throw IllegalArgumentException("Valor de depósito deve ser positivo.")
        saldo += valor
        println("Depósito de R$ $valor realizado. Novo saldo: R$ $saldo")
    }

    fun sacar(valor: Double) {
        if (valor <= 0) throw IllegalArgumentException("Valor de saque deve ser positivo.")
        if (valor > saldo) throw IllegalStateException("Saldo insuficiente para o saque.")
        saldo -= valor
        println("Saque de R$ $valor realizado. Novo saldo: R$ $saldo")
    }
}

// --- QUESTÃO 3 ---
open class Veiculo(val marca: String, val modelo: String) {
    open fun exibirInfo() {
        println("Veículo: $marca $modelo")
    }
}

class Carro(marca: String, modelo: String, val quantidadePortas: Int) : Veiculo(marca, modelo) {
    override fun exibirInfo() {
        println("Carro: $marca $modelo | Portas: $quantidadePortas")
    }
}

fun exibirDados(veiculo: Veiculo) {
    veiculo.exibirInfo()
}

// --- QUESTÃO 4 ---
abstract class Funcionario(val nome: String, val salarioBase: Double) {
    abstract fun calcularSalarioTotal(): Double
}

class Gerente(nome: String, salarioBase: Double, val bonus: Double) : Funcionario(nome, salarioBase) {
    override fun calcularSalarioTotal(): Double {
        return salarioBase + bonus
    }
}

// --- QUESTÃO 5 ---
interface FormaGeometrica {
    fun calcularArea(): Double
}

class Retangulo(val base: Double, val altura: Double) : FormaGeometrica {
    override fun calcularArea() = base * altura
}

class Circulo(val raio: Double) : FormaGeometrica {
    override fun calcularArea() = Math.PI * raio * raio
}

// --- main pra todos os testes ---
fun main() {
    println("--- Teste Questão 1 ---")
    try {
        val livro1 = Livro("Dom Casmurro", "Machado de Assis", 1899)
        livro1.exibirFicha()
        val livroInvalido = Livro("Futuro", "Desconhecido", 2050) // Lança exceção
    } catch (e: Exception) {
        println("Erro ao criar livro: ${e.message}")
    }

    println("\n--- Teste Questão 2 ---")
    val c1 = ContaBancaria(101, "Alice", 500.0)
    val c2 = ContaBancaria(102, "Bob")
    c1.depositar(200.0)
    try { c1.sacar(1000.0) } catch (e: Exception) { println("Erro: ${e.message}") }
    ContaBancaria.exibirTotalContas()

    println("\n--- Teste Questão 3 ---")
    val v = Veiculo("Yamaha", "MT-07")
    val carro = Carro("Toyota", "Corolla", 4)
    exibirDados(v)     // Polimorfismo em ação
    exibirDados(carro) // Polimorfismo em ação

    println("\n--- Teste Questão 4 ---")
    val g = Gerente("Carlos", 5000.0, 1500.0)
    println("Gerente: ${g.nome} | Salário Total: R$ ${g.calcularSalarioTotal()}")

    println("\n--- Teste Questão 5 ---")
    val formas = listOf(Retangulo(10.0, 5.0), Circulo(3.0))
    formas.forEach { 
        println("Área da forma: ${"%.2f".format(it.calcularArea())}")
    }
}