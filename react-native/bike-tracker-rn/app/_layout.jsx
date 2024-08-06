import { TouchableOpacity, Text, View, StyleSheet, ImageBackground, Modal, TextInput, Button } from 'react-native';
import React, { useState, useEffect, useRef } from 'react';
import AsyncStorage from '@react-native-async-storage/async-storage';


function App() {

  // state variables
  const [savedKM, setSavedKM] = useState(0);
  const [savedEuro, setSavedEuro] = useState(0);
  const [saved, setSaved] = useState(true);
  const [currType, setCurrType] = useState();
  const [currRatio, setCurrRatio] = useState((savedEuro/savedKM).toFixed(2));
  const [dialogVisible, setDialogVisible] = useState(false);
  const [inputValue, setInputValue] = useState(0); 
  const inputRef = useRef(null);


  // loads the initial data when the app opens
  useEffect(() => {
    const loadInitialData = async () => {
      const savedKM = await loadData('savedKM');
      if (savedKM) setSavedKM(savedKM);
      
      const savedEuro = await loadData('savedEuro');
      if (savedEuro) setSavedEuro(savedEuro);
    };
    loadInitialData();
  }, []);
  
  // util to load specific data from AsyncStorage
  const loadData = async (key) => {
    try {
      const value = await AsyncStorage.getItem(key);
      if (value !== null) {
        console.log('Data loaded ' + key + " =", value);
        return value != null ? parseFloat(value) : 0;
      }
    } catch (error) {
      console.error('Error loading data', error);
    }
  };


  // when a new value is added the saving mechanism is triggered
  useEffect(() => {
    if(!saved)
      handleSave();
  }, [saved]);

  const handleSave = () => {
    saveData('savedKM', savedKM);
    saveData('savedEuro', savedEuro);
    setSaved(true);
  };  

  // util to save specific data to AsyncStorage
  const saveData = async (key, value) => {
    try {
      await AsyncStorage.setItem(key, value.toString());
      console.log('Data saved ' + key + " =" + value.toString());
    } catch (error) {
      console.error('Error saving data', error);
    }
  };

  
  // everytime a new value is added, the ratio is recalculated
  useEffect(() => {
    updateRatio();
    }, [savedKM, savedEuro]);

  const updateRatio = () => {
    let ratio = 0;
    if (savedKM > 0) ratio = (savedEuro / savedKM).toFixed(2);
    setCurrRatio(ratio);
  }


 // ui handling
 useEffect(() => {
  if(dialogVisible && inputRef.current)
    setTimeout(() => {
      inputRef.current.focus();
    }, 100);
  }, [dialogVisible]);
 
  const showDialog = (type) => {
    setInputValue(0);
    setDialogVisible(true);
    setCurrType(type);
  };

  const handleCancel = () => {
    setDialogVisible(false);
  };

  const handleConfirm = () => {
    console.log(inputValue);
   if(currType == "KM"){
    setSavedKM(parseFloat(savedKM) + parseFloat(inputValue));
    console.log(savedKM, inputValue);
   } else if(currType == "€"){
    setSavedEuro(parseFloat(savedEuro) + parseFloat(inputValue));   
   }
   setDialogVisible(false);
   setSaved(false);
  };


  return (
    <View style={styles.container}>
      <ImageBackground 
        style={styles.imageBackground}
        source={require('../assets/bg01.jpg')}
      >
        <View style={styles.mainView} >
          <View style={styles.ratioContainer}>
            <Text style={styles.ratioText}>{currRatio} €/KM</Text>
          </View>
          <View>
            <TouchableOpacity 
              style={styles.buttons}  
              onPress={() => showDialog("KM")}
            >
              <Text className="text-3xl text-center">{savedKM} KM</Text>
            </TouchableOpacity>
          </View>
          <View>
            <TouchableOpacity 
              style={styles.buttons} 
              onPress={() => showDialog("€")}
            >
              <Text className="text-3xl text-center">{savedEuro} €</Text>
            </TouchableOpacity>
          </View>
        </View>

        <Modal
          transparent={true}
          animationType="slide"
          visible={dialogVisible}
          onRequestClose={handleCancel}
        >
          <View style={styles.dialog}>
            <Text style={styles.dialogTitle}>ADD TO {currType}</Text>
            <TextInput
              ref={inputRef}
              style={styles.input}
              keyboardType ="numeric"
              value={inputValue}
              onChangeText={setInputValue}
            />
            <View style={styles.buttonsContainer}>
              <Button title="Cancel" onPress={handleCancel} />
              <Button title="Confirm" onPress={handleConfirm} />
            </View>
          </View>
        </Modal>
      </ImageBackground>
    </View> 
  );
};
      
  const styles = StyleSheet.create({   
    container: {
      flex: 1
    },
    imageBackground: {
      flex: 1,
      widht: '100%',
      justifyContent: 'center',
      alignItems: 'center'
    },
    mainView: {
      backgroundColor: 'rgba(241,245,249, 0.90)',
      borderWidth: 1,
      borderColor: 'rgba(99, 102, 241, 0.15)',
      justifyContent: 'center',
      alignItems: 'center',
      borderRadius: 10,
      paddingVertical: 20,
      paddingHorizontal: 5 
    },
    ratioText: {
      fontSize: 36,
      marginBottom: 20
    },
    buttons: {
      backgroundColor: 'white',
      borderRadius: 5,   
      margin: 15,
      padding: 5,
      width: 200,
      borderWidth: 2,
      borderColor: 'rgba(99, 102, 241, 0.25)',
    },
    dialog: {
      flex: 1,
      justifyContent: 'center',
      alignItems: 'center',
      backgroundColor: 'rgba(0,0,0,0.9)',
    },
    dialogTitle: {
      fontSize: 24,
      marginBottom: 10,
      color: 'white',
    },
    input: {
      height: 50,
      borderColor: 'gray',
      borderWidth: 1,
      width: '40%',
      marginBottom: 20,
      backgroundColor: 'white',
      paddingHorizontal: 10,
      borderRadius: 10,
      textAlign:'center',
      fontSize:24,
    },
    buttonsContainer: {
      flexDirection: 'row',
      justifyContent: 'space-evenly',
      width: '45%',
    },
  });

    export default App;