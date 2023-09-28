import java.time.LocalDate;

class Fecha {
    LocalDate currentDate = LocalDate.now();

    private int dia;
    private int mes;
    private int año;

    Fecha(int dia, int mes, int año) {
        if (fechaCorrecta(dia, mes, año)) {
            this.dia = dia;
            this.mes = mes;
            this.año = año;
        } else {
            System.out.println("La fecha " + toString(dia, mes, año) + " no es correcta. Se cambia a la fecha actual.");
            this.dia = currentDate.getDayOfMonth();
            this.mes = currentDate.getMonthValue();
            this.año = currentDate.getYear();
        }

    }

    Fecha() {
        this.dia = currentDate.getDayOfMonth();
        this.mes = currentDate.getMonthValue();
        this.año = currentDate.getYear();
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getaño() {
        return año;
    }

    public void setaño(int año) {
        this.año = año;
    }

    @Override
    public String toString() {
        return String.format("%02d", this.dia) + "/" + String.format("%02d", this.mes) + "/"
                + String.format("%04d", this.año);
    }

    private String toString(int dia, int mes, int año) {
        return String.format("%02d", dia) + "/" + String.format("%02d", mes) + "/" + String.format("%04d", año);
    }

    private boolean fechaCorrecta() {
        boolean fechaCorrecta;
        switch (this.mes) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                fechaCorrecta = (this.dia >= 1 && this.dia <= 31 ? true : false);
                break;
            case 2:
                fechaCorrecta = (this.dia >= 1 && this.dia <= (esBisiesto() ? 29 : 28) ? true : false);
                break;
            case 4: case 6: case 9: case 11:
                fechaCorrecta = (this.dia >= 1 && this.dia <= 30 ? true : false);
                break;
            default:
                fechaCorrecta = false;
                break;
        }
        return (this.año > 0 ? fechaCorrecta : false);
    }

    private boolean fechaCorrecta(int dia, int mes, int año) {
        boolean fechaCorrecta;
        switch (mes) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                fechaCorrecta = (dia >= 1 && dia <= 31 ? true : false);
                break;
            case 2:
                fechaCorrecta = (dia >= 1 && dia <= (esBisiesto(año) ? 29 : 28) ? true : false);
                break;
            case 4: case 6: case 9: case 11:
                fechaCorrecta = (dia >= 1 && dia <= 30 ? true : false);
                break;
            default:
                fechaCorrecta = false;
                break;
        }
        return (año > 0 ? fechaCorrecta : false);
    }

    private boolean esBisiesto() {
        return esBisiesto(this.año);
    }

    private boolean esBisiesto(int año) {
        boolean esBisiesto = false;
        if ((año % 4 == 0 && año % 100 != 0) || (año % 400 == 0)) {
            esBisiesto = true;
        }
        return esBisiesto;
    }

    void diaSiguiente() {
        this.dia++;
        if (!fechaCorrecta()) {
            this.dia = 1;
            this.mes++;
            if (!fechaCorrecta()) {
                this.mes = 1;
                this.año++;
            }
        }
    }

    void diaAnterior() {
        this.dia--;
        if (!fechaCorrecta()) {
            this.dia = ultimoDia(this.mes > 1 ? this.mes - 1 : 12);
            this.mes--;
            if (!fechaCorrecta()) {
                this.mes = 12;
                this.año--;
            }
        }
    }

    private int ultimoDia(int mes) {
        int ultimoDia = 0;
        switch (mes) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                ultimoDia = 31;
                break;
            case 2:
                ultimoDia = (esBisiesto(this.año) ? 29 : 28);
                break;
            case 4: case 6: case 9: case 11:
                ultimoDia = 30;
                break;
        }
        return ultimoDia;
    }

    private boolean fechaFutura(int dia, int mes, int año) {
        boolean fechaFutura = false;
        if ((año > this.año) || (año == this.año && mes > this.mes)
                || (año == this.año && mes == this.mes && dia > this.dia)) {
            fechaFutura = true;
        }
        return fechaFutura;
    }

    private boolean fechaPasada(int dia, int mes, int año) {
        boolean fechaPasada = false;
        if ((año < this.año) || (año == this.año && mes < this.mes) || (año == this.año && mes == this.mes && dia < this.dia)) {
            fechaPasada = true;
        }
        return fechaPasada;
    }

    public String diasHasta(int dia, int mes, int año) {
        boolean fechaCorrecta = fechaCorrecta(dia, mes, año);
        boolean fechaFutura = fechaFutura(dia, mes, año);

        if (!fechaCorrecta) {
            return "La fecha " + toString(dia, mes, año) + " no es valida: formato no valido";
        } else if (!fechaFutura) {
            return "La fecha " + toString(dia, mes, año) + " no es valida: solo fechas futuras";
        } else {
            int dias = 0;
            while (!((this.dia == dia) && (this.mes == mes) && (this.año == año))) {
                this.diaSiguiente();
                dias++;
            }
            return "Quedan " + dias + " dias hasta el " + toString();
        }
    }

    public String diasDesde(int dia, int mes, int año) {
        boolean fechaCorrecta = fechaCorrecta(dia, mes, año);
        boolean fechaPasada = fechaPasada(dia, mes, año);

        if (!fechaCorrecta) {
            return "La fecha " + toString(dia, mes, año) + " no es valida: formato no valido";
        } else if (!fechaPasada) {
            return "La fecha " + toString(dia, mes, año) + " no es valida: solo fechas pasadas";
        } else {
            int dias = 0;
            while (!((dia == this.dia) && (mes == this.mes) && (año == this.año))) {
                this.diaAnterior();
                dias++;
            }
            return "Han pasado " + dias + " dias desde el " + toString();
        }
    }
}
