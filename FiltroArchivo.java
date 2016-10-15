/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAINT2;


import java.io.File;
import javax.swing.filechooser.FileFilter;

public class FiltroArchivo extends FileFilter {
     
    public FiltroArchivo(){
    }

    @Override
    public boolean accept(File archivo) {
        if (archivo.isDirectory()) {
            return true;
        }

        String s = archivo.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            String extension = s.substring(i+1).toLowerCase();
            if ("png".equals(extension)){
                    return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "*.png";
    }
}