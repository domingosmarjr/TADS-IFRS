// static -> proibir a instanciação fazer new Strings()
static class Strings {

    public static string Capitalizar(string entrada) {
        string nova = "";
        for (int i = 0; i < entrada.Length; i++) {
            if (i == 0
                  && entrada[i] >= 'a' 
                  && entrada[i] <= 122) {
                      // deslocar 'n' = 110 - 32 'N' = 78
                nova +=  (char) (entrada[i] - 32); 
            } else {
                nova += entrada[i]; // copiar como está 
            }
        }
        return nova;
    }
}