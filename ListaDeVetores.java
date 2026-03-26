public class ListaDeVetores<Tipo> {
    Tipo[] vetor;
    int capacidade;
    int tamanho;

    public ListaDeVetores(int capacidade) {
        this.capacidade = capacidade;
        this.tamanho = 0;
        this.vetor = (Tipo[]) new Object[capacidade];
    }

    public Boolean add(Tipo objeto){
        if(tamanho == capacidade){
            grow();
        }
        vetor[tamanho++] = objeto;
        return true;
    }

    public void grow() {
        this.capacidade += this.capacidade / 2;
        Tipo[] novoVetorzinho = (Tipo[]) new Object[this.capacidade];
        for (int i = 0; i < this.tamanho; i++) {
            novoVetorzinho[i] = this.vetor[i];
        } 
        this.vetor = novoVetorzinho;
    }

    public Boolean add(int index, Tipo objeto){
        if (index < 0 || index > this.tamanho) {
            throw new IndexOutOfBoundsException("Indice Invalido: " + index);
        }
        if(tamanho == capacidade){
            grow();
        }
        for (int i = tamanho; i > index; i--) {
            vetor[i] = vetor[i - 1];
        }
        vetor[index] = objeto;
        tamanho++;
        return true;
    }

    public Boolean remove(int index){
        if (index < 0 || index >= this.tamanho) {
            throw new IndexOutOfBoundsException("Indice Invalido: " + index);
        }
        for (int i = index; i < tamanho; i++) {
            vetor[i] = vetor[i + 1];
        }
        tamanho--;
        return true; 
    }

    public Boolean remove(Tipo objeto) {
        int index = indexOf(objeto);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    public Boolean set(int index, Tipo objeto) {
        if (index < 0 || index >= this.tamanho) {
            throw new IndexOutOfBoundsException("Indice Invalido: " + index);
        }
        vetor[index] = objeto;
        return true;
    }

    public Tipo get(int index){
        return this.vetor[index];
    }

    public Boolean contains(Tipo objeto){
        for(int i = 0; i < this.tamanho;i++){
            if(vetor[i] == objeto){
                return true;
            }
        }
        return false;
    }

    public int indexOf(Tipo objeto){
        for(int i = 0; i < this.tamanho;i++){
            if(vetor[i] == objeto){
                return i;
            }
        }
        return -1;
    }

    public Tipo[] toArray(){
        Tipo[] array_estatico = (Tipo[]) new Object[this.vetor.length];
        int i = 0;
        while (i < this.tamanho){
            array_estatico[i] = this.vetor[i];
            i++;
        }
        return array_estatico;
    }

}
