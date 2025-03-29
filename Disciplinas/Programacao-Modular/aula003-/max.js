// Toda definição/declaração tem um ESCOPO

// export -> tornar função visível fora do próprio arquivo
// Se a função exporta, o outro lado, que vai usar ela, importa!
// Não se exporta tudo! Tudo que é exportado se torna público (info sensíveis)
// Por padrão se exporta NADA! -> "O que eu preciso exportar?"

export function max (a, b) {
    return (a > b) ? a : b;
}
