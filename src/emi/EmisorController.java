/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author bramdon
 */
public class EmisorController implements Initializable {

    @FXML TextField carac;
    @FXML TextField divisor;
    @FXML ComboBox com;
    @FXML Label conversion;
    @FXML Label ham;
    @FXML Label crc;
    char caracter,caracH,caracD;
    int cara;
    int[]bina=new int[8];
    int[]hamming=new int[12];
    String b="",h="",c="",res="";
    @FXML private void realizar()
    {
        caracter=(carac.getText()).charAt(0);
        cara=(int) (caracter);
        System.out.print(caracter+"->"+cara+"->");
        binario();
        conversion.setText(caracter+"->"+cara+"->"+b);
        espaciosBits();
        if(com.getValue()==("Par"))
        {
            System.out.println("\nEscogio paridad par");
            hamminPar();
            ham.setText("Hammin es "+h);
            operacionCRC();
        }
        else
        {
            System.out.println("\nEscogio paridad impar");
            hamminImpar();
            ham.setText("Hammin es "+h);
            operacionCRC();
        }
    }
    public void binario()
    {
        int i=7,aux=cara;
        b="";
        while(i>=0)
        {
            if(aux%2==0)
                bina[i]=0;
            else
                bina[i]=1;
            i--;
            aux/=2;
        }
        for(int j=0;j<8;j++)
            b=b+Integer.toString(bina[j]);
    }
    public void espaciosBits()
    {
        hamming[2]=bina[0];
        hamming[4]=bina[1];
        hamming[5]=bina[2];
        hamming[6]=bina[3];
        hamming[8]=bina[4];
        hamming[9]=bina[5];
        hamming[10]=bina[6];
        hamming[11]=bina[7];
        
    }
    
    public void hamminPar()
    {
        h="";
        //primer espacion
        int contador=0;
        for(int i=2;i<11;i+=2)
            if(hamming[i]==1)
                contador++;
        if(contador%2==0)
            hamming[0]=0;
        else
            hamming[0]=1;
        
        /*for(int j=0;j<12;j++)
            System.out.println("Hamin pos #"+(j+1)+" su dato es "+hamming[j]);*/
        
        //segundo espacio
        contador=0;
        if(hamming[2]==1)
            contador++;
        if(hamming[5]==1)
            contador++;
        if(hamming[6]==1)
            contador++;
        if(hamming[9]==1)
            contador++;
        if(hamming[10]==1)
            contador++;
        
        if(contador%2==0)
            hamming[1]=0;
        else
            hamming[1]=1;
        
        /*for(int j=0;j<12;j++)
            System.out.println("Hamin pos #"+(j+1)+" su dato es "+hamming[j]);*/
        
        //tercer espacio
        contador=0;
        for(int i=4;i<7;i++)
            if(hamming[i]==1)
                contador++;
            
        if(hamming[11]==1)
            contador++;
        if(contador%2==0)
            hamming[3]=0;
        else
            hamming[3]=1;
        
        /*for(int j=0;j<12;j++)
            System.out.println("Hamin pos #"+(j+1)+" su dato es "+hamming[j]);*/
        
        //cuarto espacio
        contador=0;
        for(int i=8;i<12;i++)
            if(hamming[i]==1)
                contador++;
        if(contador%2==0)
            hamming[7]=0;
        else
            hamming[7]=1;
        
        for(int j=0;j<12;j++)
            h=h+Integer.toString(hamming[j]);
    }
    
    public void hamminImpar()
    {
        h="";
        //primer espacion
        int contador=0;
        for(int i=2;i<11;i+=2)
            if(hamming[i]==1)
                contador++;
        
        if(contador%2!=0)
            hamming[0]=0;
        else
            hamming[0]=1;
        
        /*for(int j=0;j<12;j++)
            System.out.println("Hamin pos #"+(j+1)+" su dato es "+hamming[j]);*/
        
        //segundo espacio
        contador=0;
        if(hamming[2]==1)
            contador++;
        if(hamming[5]==1)
            contador++;
        if(hamming[6]==1)
            contador++;
        if(hamming[9]==1)
            contador++;
        if(hamming[10]==1)
            contador++;
        
        if(contador%2!=0)
            hamming[1]=0;
        else
            hamming[1]=1;
        
        /*for(int j=0;j<12;j++)
            System.out.println("Hamin pos #"+(j+1)+" su dato es "+hamming[j]);*/
        
        //tercer espacio
        contador=0;
        for(int i=4;i<7;i++)
            if(hamming[i]==1)
                contador++;
            
        if(hamming[11]==1)
            contador++;
        if(contador%2!=0)
            hamming[3]=0;
        else
            hamming[3]=1;
        
        /*for(int j=0;j<12;j++)
            System.out.println("Hamin pos #"+(j+1)+" su dato es "+hamming[j]);*/
        
        //cuarto espacio
        contador=0;
        for(int i=8;i<12;i++)
            if(hamming[i]==1)
                contador++;
        if(contador%2!=0)
            hamming[7]=0;
        else
            hamming[7]=1;
        
        for(int j=0;j<12;j++)
            h=h+Integer.toString(hamming[j]);
    }
    
    public void operacionCRC()
    {
        String divi=divisor.getText();
        System.out.println(divi);
        String cad=h;
        int j=1;
        
        for(int i=0;i<divi.length()-1;i++)
            h=h+"0";
        
        while(divi.length()<=h.length())
        {
            int aux=h.indexOf("1");
            res="";
            for(int i=0;i<divi.length();i++)
            {
                caracH=h.charAt(i+aux);
                caracD=divi.charAt(i);
                if(caracH==caracD)
                    res=res+"0";
                else
                    res=res+"1";
            }
            h=h.substring(aux+divi.length(),h.length());
            h=res+h;
            aux=h.indexOf("1");
            if(aux>-1)
                h=h.substring(aux,h.length());
            else
                h="";
            System.out.println("Vuelta "+(j++)+" h tiene el valor de "+h);
        }
        
        while(h.length()<divi.length()-1)
            h="0"+h;
        
        c=cad+h;
        System.out.println("EL valor con CRC es "+c);
        crc.setText("El valor con CRC es "+c);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        com.getItems().add("Par");
        com.getItems().add("Impar");
    }    
    
}
