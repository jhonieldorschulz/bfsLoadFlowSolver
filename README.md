# Power Flow Solution (Backward Forward Sweep)

This is a Java implementation of the **Backward Forward Sweep** method for solving power flow in electrical distribution networks. The method is widely used for radial distribution systems due to its simplicity and efficiency in handling the nonlinearity of load flow problems.

## Backward Forward Sweep Method

The **Backward Forward Sweep (BFS)** method is an iterative algorithm used to solve power flow in radial distribution systems. It involves two main steps:
- **Backward Sweep**: Computes currents starting from the leaf nodes (loads) toward the source (substation).
- **Forward Sweep**: Updates voltages by moving from the source to the leaf nodes.

This method is commonly used in power distribution networks to ensure proper voltage levels and system stability.

## Features
- Efficient solution for radial distribution power flow problems.
- Can be used as a library or integrated as an API in other Java projects.

## Requirements

- Java 8 or later
- Maven

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/jhonieldorschulz/bfsLoadFlowSolver.git
   ```
2. Navigate to the project directory:
   ```bash
   cd bfsLoadFlowSolver
   ```
3. Compile the project using Maven:
   ```bash
   mvn clean install
   ```

## Usage

### As a Library
You can integrate the `.jar` file into your own Java projects by including it as a dependency. After compiling, add the generated `.jar` located in the `target` folder to your project.

### As an API
The project can be executed as an API to expose power flow calculations. You can implement endpoints in a RESTful service to interact with this solution.

## Example
Run the project using:
```bash
java -jar target/bfsLoadFlowSolver.jar
```

## Contributing

Contributions are welcome! Feel free to open issues or submit pull requests.

--- 

This README now includes both the description of the method and how the project can be utilized in different contexts.
