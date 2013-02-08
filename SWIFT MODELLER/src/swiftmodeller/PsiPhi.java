package swiftmodeller;

import java.io.*;
import java.util.*;
import java.awt.Component;
import org.biojava.bio.structure.io.PDBFileReader;
import org.biojava.bio.structure.*;

public class PsiPhi
{
    public PsiPhi(String file, Component co)
    {
            String filename = new String(workpath + sep + file);
            String str = new String(workpath + sep + file.substring(0, file.indexOf(".")) + ".psiphi");
            PDBFileReader pdbreader = new PDBFileReader();
            BufferedWriter writer = null;
            StringBuffer sb = new StringBuffer();

            try
            {
                Structure struct = pdbreader.getStructure(filename);
                Chain chain  = struct.getChain(0);
                List groups = chain.getAtomGroups("amino");

                AminoAcid a;
                AminoAcid b;
                AminoAcid c;

                for(int i=0; i < groups.size(); i++)
                {

                    b = (AminoAcid)groups.get(i);

                    double phi = 360.0;
                    double psi = 360.0;

                    if(i > 0)
                    {
                        a = (AminoAcid)groups.get(i-1);
                        phi = Calc.getPhi(a,b);
                    }
                    if(i < groups.size()-1)
                    {
                        c = (AminoAcid)groups.get(i+1);
                        psi = Calc.getPsi(b,c);
                    }

                    sb.append(phi + "\t" + psi + "\n");
                }

                writer = new BufferedWriter(new FileWriter(str));
                writer.write(sb.toString());
                writer.close();
            }
            catch(IOException e)
            {
                new ErrorClose(co, true, "I/O Error");
            }
            catch(StructureException e)
            {
                new ErrorClose(co, true, "Unable to parse model PDB file");
            }
    }

    private String workpath = SwiftModellerView.workpath;
    private String sep = System.getProperty("file.separator");
}