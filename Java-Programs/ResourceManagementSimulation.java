import org.cloudbus.cloudsim.*;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.provisioners.*;

import java.util.*;

public class ResourceManagementSimulation {

    public static void main(String[] args) {

        try {
            // Step 1: Initialize CloudSim
            int numUsers = 1;
            Calendar calendar = Calendar.getInstance();
            boolean traceFlag = false;
            CloudSim.init(numUsers, calendar, traceFlag);

            // Step 2: Create Datacenter
            Datacenter datacenter = createDatacenter("Datacenter_1");

            // Step 3: Create Broker
            DatacenterBroker broker = createBroker();
            int brokerId = broker.getId();

            // Step 4: Create VMs
            List<Vm> vmList = new ArrayList<Vm>();
            for (int i = 0; i < 2; i++) {
                Vm vm = new Vm(
                        i,
                        brokerId,
                        1000,      // MIPS
                        1,         // Number of PEs
                        512,       // RAM (MB)
                        1000,      // Bandwidth
                        10000,     // Storage
                        "Xen",
                        new CloudletSchedulerTimeShared()
                );
                vmList.add(vm);
            }
            broker.submitVmList(vmList);

            // Step 5: Create Cloudlets
            List<Cloudlet> cloudletList = new ArrayList<Cloudlet>();
            for (int i = 0; i < 4; i++) {
                Cloudlet cloudlet = new Cloudlet(
                        i,
                        4000,      // Cloudlet length
                        1,         // PEs
                        300,       // File size
                        300,       // Output size
                        new UtilizationModelFull(),
                        new UtilizationModelFull(),
                        new UtilizationModelFull()
                );
                cloudlet.setUserId(brokerId);
                cloudletList.add(cloudlet);
            }
            broker.submitCloudletList(cloudletList);

            // Step 6: Start Simulation
            CloudSim.startSimulation();
            CloudSim.stopSimulation();

            // Step 7: Print Results
            System.out.println("\n========== CLOUDLET EXECUTION RESULTS ==========");
            for (Cloudlet c : broker.getCloudletReceivedList()) {
                System.out.println(
                        "Cloudlet ID: " + c.getCloudletId() +
                        " | VM ID: " + c.getVmId() +
                        " | Status: " + Cloudlet.getStatusString(c.getStatus())
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Create Datacenter
    private static Datacenter createDatacenter(String name) {

        List<Pe> peList = new ArrayList<Pe>();
        peList.add(new Pe(0, new PeProvisionerSimple(1000)));

        Host host = new Host(
                0,
                new RamProvisionerSimple(4096),
                new BwProvisionerSimple(10000),
                1000000,
                peList,
                new VmSchedulerTimeShared(peList)
        );

        List<Host> hostList = new ArrayList<Host>();
        hostList.add(host);

        DatacenterCharacteristics characteristics =
                new DatacenterCharacteristics(
                        "x86",
                        "Linux",
                        "Xen",
                        hostList,
                        10.0,
                        3.0,
                        0.05,
                        0.001,
                        0.0
                );

        try {
            return new Datacenter(
                    name,
                    characteristics,
                    new VmAllocationPolicySimple(hostList),
                    new ArrayList<Storage>(),
                    0
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Create Broker
    private static DatacenterBroker createBroker() {
        try {
            return new DatacenterBroker("Broker");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
