package org.ucb.c5.composition;

import org.ucb.c5.composition.model.RBSOption;
import org.ucb.c5.composition.model.Host;
import org.ucb.c5.composition.model.Construct;
import org.ucb.c5.composition.model.Transcript;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.ucb.c5.composition.model.Composition;

/**
 * Constructs an encoding DNA for a (co)cistronic operon described by a
 * Composition object that describes a specification for an engineered organism
 *
 * @author J. Christopher Anderson
 */
public class CompositionToDNA {

    private TranscriptDesigner swo;
    
    public void initiate() throws Exception {
        swo = new TranscriptDesigner();
        swo.initiate();
    }

    public Construct run(Composition comp) throws Exception {
        List<String> proteins = comp.getProteins();
        Host organism = comp.getHost();
        
        //Construct an mRNA for each peptide
        List<Transcript> mRNAs = new ArrayList<>();
        Set<RBSOption> ignores = new HashSet<>();
        for (String peptide : proteins) {
            Transcript mrna = swo.run(peptide, ignores);
            ignores.add(mrna.getRbs()); //Add this rbs to excludes so it is not repeated
            mRNAs.add(mrna);
        }

        //Construct the output dna
        Construct out = new Construct();
        out.mRNAs = mRNAs;
        out.promoter = comp.getPromoter();
        out.terminator = comp.getTerminator();
        return out;
    }

    public static void main(String[] args) throws Exception {
        //Construct the composition object
        String Pbad_Promoter = "ttatgacaacttgacggctacatcattcactttttcttcacaaccggcacggaactcgctcgggctggccccggtgcattttttaaatacccgcgagaaatagagttgatcgtcaaaaccaacattgcgaccgacggtggcgataggcatccgggtggtgctcaaaagcagcttcgcctggctgatacgttggtcctcgcgccagcttaagacgctaatccctaactgctggcggaaaagatgtgacagacgcgacggcgacaagcaaacatgctgtgcgacgctggcgatatcaaaattgctgtctgccaggtgatcgctgatgtactgacaagcctcgcgtacccgattatccatcggtggatggagcgactcgttaatcgcttccatgcgccgcagtaacaattgctcaagcagatttatcgccagcagctccgaatagcgcccttccccttgcccggcgttaatgatttgcccaaacaggtcgctgaaatgcggctggtgcgcttcatccgggcgaaagaaccccgtattggcaaatattgacggccagttaagccattcatgccagtaggcgcgcggacgaaagtaaacccactggtgataccattcgcgagcctccggatgacgaccgtagtgatgaatctctcctggcgggaacagcaaaatatcacccggtcggcaaacaaattctcgtccctgatttttcaccaccccctgaccgcgaatggtgagattgagaatataacctttcattcccagcggtcggtcgataaaaaaatcgagataaccgttggcctcaatcggcgttaaacccgccaccagatgggcattaaacgagtatcccggcagcaggggatcattttgcgcttcagccatacttttcatactcccgccattcagagaagaaaccaattgtccatattgcatcagacattgccgtcactgcgtcttttactggctcttctcgctaaccaaaccggtaaccccgcttattaaaagcattctgtaacaaagcgggaccaaagccatgacaaaaacgcgtaacaaaagtgtctataatcacggcagaaaagtccacattgattatttgcacggcgtcacactttgctatgccatagcatttttatccataagattagcggatcctacctgacgctttttatcgcaactctctactgtttctccatacccgtttttttgggctagc";
        String TrrnB_Terminator = "TGCCTGGCGGCAGTAGCGCGGTGGTCCCACCTGACCCCATGCCGAACTCAGAAGTGAAACGCCGTAGCGCCGATGGTAGTGTGGGGTCTCCCCATGCGAGAGTAGGGAACTGCCAGGCATCAAATAAAACGAAAGGCTCAGTCGAAAGACTGGGCCTT";

        ArrayList<String> proteins = new ArrayList<>();
        proteins.add("MSLEREEPQHFGAGPAQMPTPVLQQAAKDLINFNDIGLGIGEISHRSKDATKVIEDSKKHLIELLNIPDTHEVFYLQGGGTTGFSSVATNLAAAYVGKHGKIAPAGYLVTGSWSQKSFEEAKRLHVPAEVIFNAKDYNNGKFGKIPDESLWEDKIKGKAFSYVYLCENETVHGVEWPELPKCLVNDPNIEIVADLSSDILSRKIDVSQYGVIMAGAQKNIGLAGLTLYIIKKSILKNISGASDETLHELGVPITPIAFDYPTVVKNNSAYNTIPIFTLHVMDLVFQHILKKGGVEAQQAENEEKAKILYEALDANSDFYNVPVDPKCRSKMNVVFTLKKDGLDDQFLKEAAARHLTGLKGHRSVGGFRASIYNALSVKTVQNLVDFIKEFAEKNA");
        proteins.add("MGRFILKCLKCGREYSQEYRLTCENDDSFLRAEYLEKKLELRKQPGIGRFHSWLPVQEELTTEAGPITYKSEALARELGLSNLYIGFSGYWPEKGAFIKTCSFKELEAHPTMQLLKESGGKAIVLASAGNTGRAFAHVSALTGTDVYIVVPDSGIPKLWLPEEPTDSIHLISMTPGNDYTDAINLAGRIAKLPGMVPEGGARNVARREGMGTVMLDAAVTIGKMPDHYFQAVGSGTGGISAWEASLRLREDGRFGSKLPKLQLTQNLPFVPMYNAWQEGRRDIIPEIDMKDAKKRIEETYATVLTNRAPPYSVTGGLYDALVDTDGIMYAVSKEEALDAKALFESLEGIDILPPSAVAAASLLKAVEAGNVGKDDTILLNIAGGGFKRLKEDFTLFQIEPEITVSNPDVPLEELKL");
        proteins.add("MADSKPLRTLDGDPVAVEALLRDVFGIVVDEAIRKGTNASEKVCEWKEPEELKQLLDLELQSQGESRERILERCRAVIHYSVKTGHPRFFNQLFSGLDPHALAGRIITESLNTSQYTYEIAPVFVLMEEEVLKKLRALVGWNTGDGVFCPGGSISNMYAINLARFQRYPDCKQRGLRALPPLALFTSKECHYSITKGAAFLGLGTDSVRVVKADERGKMIPEDLERQISLAEAEGSVPFLVSATSGTTVLGAFDPLDAIADVCQRHGLWLHVDAAWGGSVLLSRTHRHLLDGIQRADSVAWNPHKLLAAGLQCSALLLRDTSNLLKRCHGSQASYLFQQDKFYNVALDTGDKVVQCGRRVDCLKLWLMWKAQGGQGLEWRIDQAFALTRYLVEEIKKREGFELVMEPEFVNVCFWFVPPSLRGKKESPDYSQRLSQVAPVLKERMVKKGTMMIGYQPHGTRANFFRMVVANPILVQADIDFLLGELERLGQDL");
        
        Composition comp = new Composition(Host.Ecoli, Pbad_Promoter, proteins, TrrnB_Terminator);
        
        //Instantiate and run this algorithm
        CompositionToDNA c2d = new CompositionToDNA();
        c2d.initiate();
        Construct dna = c2d.run(comp);
        
        //Print out the results
        System.out.println(dna.toSeq());
    }
}
