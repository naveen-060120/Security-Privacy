  
import org.cloudbus.cloudsim.*; 
import org.cloudbus.cloudsim.core.CloudSim; 
import org.cloudbus.cloudsim.provisioners.*; 
import java.util.*; 
 
public class CustomSchedulingSimulation { 
 
    public static void main(String[] args) { 
 
        CloudSim.init(1, Calendar.getInstance(), false); 
 
        List<Pe> pe = Arrays.asList(new Pe(0,new PeProvisionerSimple(1000))); 
        Host h = new Host(0,new RamProvisionerSimple(2048), 
                new BwProvisionerSimple(10000),1000000, 
                pe,new VmSchedulerTimeShared(pe)); 
  
        Datacenter dc; 
        try { 
            dc = new Datacenter("DC", 
                    new DatacenterCharacteristics( 
                            "x86","Linux","Xen", 
                            Arrays.asList(h),10,3,0.05,0.001,0), 
                    new VmAllocationPolicySimple(Arrays.asList(h)), 
                    new ArrayList<>(),0); 
        } catch(Exception e){ return; } 
 
        DatacenterBroker b; 
        try { b = new DatacenterBroker("B"); } 
        catch(Exception e){ return; } 
 
        b.submitVmList(Arrays.asList( 
                new Vm(0,b.getId(),1000,1,512,1000,10000, 
                        "Xen",new CloudletSchedulerTimeShared()) 
        )); 
 
        List<Cloudlet> cl = Arrays.asList( 
                new Cloudlet(0,8000,1,300,300, 
                        new UtilizationModelFull(), 
                        new UtilizationModelFull(), 
                        new UtilizationModelFull()), 
                new Cloudlet(1,2000,1,300,300, 
                        new UtilizationModelFull(), 
                        new UtilizationModelFull(), 
                        new UtilizationModelFull()) 
        ); 
        cl.forEach(c -> c.setUserId(b.getId())); 
 
        cl.sort(Comparator.comparingLong(Cloudlet::getCloudletLength)); 
 
        b.submitCloudletList(cl); 
 
        CloudSim.startSimulation(); 
        CloudSim.stopSimulation(); 
 
        b.getCloudletReceivedList() 
         .forEach(c -> System.out.println( 
             "Cloudlet "+c.getCloudletId()+" finished")); 
    } 
}